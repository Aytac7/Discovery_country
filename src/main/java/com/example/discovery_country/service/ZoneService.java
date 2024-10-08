package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ZoneEntity;
import com.example.discovery_country.dao.repository.RegionRepository;
import com.example.discovery_country.dao.repository.ZoneRepository;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.mapper.ZoneMapper;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.request.ZoneRequest;
import com.example.discovery_country.model.dto.response.ZoneResponse;
import com.example.discovery_country.service.specification.ZoneSpecification;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZoneService {
    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    public ZoneResponse create(ZoneRequest request) {
        log.info("ActionLog.createZone start");

        ZoneEntity zoneEntity = zoneMapper.mapToEntity(request);
        ZoneEntity zone = zoneRepository.save(zoneEntity);
        ZoneResponse zoneResponse = zoneMapper.mapToResponse(zone,null);
        log.info("ActionLog.createZone end");
        return zoneResponse;

    }

    public Page<ZoneResponse> getZones(CriteriaRequestForName criteriaRequest, Pageable pageable) {
        log.info("ActionLog.getZone start");

        Specification<ZoneEntity> spec = ZoneSpecification.getZoneByCriteria(criteriaRequest);
        Page<ZoneEntity> zoneEntities = zoneRepository.findAll(spec, pageable);
        List<ZoneResponse> list = zoneEntities.map(i -> zoneMapper.mapToResponse(i, criteriaRequest.getKey())).toList();
        log.info("ActionLog.getZone end");
        return new PageImpl<>(list);

    }

    public ZoneResponse update(Long id, ZoneRequest zoneRequest) {

        log.info("ActionLog.updateZone start with id#" + id);

        ZoneEntity zoneEntity = zoneRepository.findById(id).orElseThrow(() -> new RuntimeException(HttpStatus.NOT_FOUND.name()));
        zoneMapper.mapForUpdate(zoneEntity, zoneRequest);
        zoneEntity = zoneRepository.save(zoneEntity);
        ZoneResponse zoneResponse = zoneMapper.mapToResponse(zoneEntity,null);
        log.info("ActionLog.updateZone end");
        return zoneResponse;
    }

    public void delete(Long id) {
        log.info("ActionLog.deleteZone start with id#" + id);
        zoneRepository.deleteById(id) ;
        log.info("ActionLog.updateZone end");
    }


}
