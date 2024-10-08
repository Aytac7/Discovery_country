package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.entity.RoomEntity;
import com.example.discovery_country.dao.repository.RoomRepository;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.exception.RoomNotFoundException;
import com.example.discovery_country.helper.IncreaseViewCount;

import com.example.discovery_country.mapper.RoomMapper;
import com.example.discovery_country.model.dto.criteria.RoomCriteriaRequest;
import com.example.discovery_country.model.dto.request.RoomRequest;
import com.example.discovery_country.model.dto.response.RegionResponse;
import com.example.discovery_country.model.dto.response.RoomResponseFindById;
import com.example.discovery_country.model.dto.response.RoomResponse;

import com.example.discovery_country.service.specification.RoomSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {
    public final RoomRepository roomRepository;
    public final ImageRepository imageRepository;
    private final RoomMapper roomMapper;


    public RoomResponse create(RoomRequest request) {
        log.info("ActionLog.createRoom start");
        RoomEntity room = roomRepository.save(roomMapper.mapToEntity(request, imageRepository.findAllById(request.getImageIds())));
        RoomResponse roomResponse = roomMapper.mapToResponse(room, LangType.AZ);
        log.info("ActionLog.createRoom end");
        return roomResponse;
    }

    public Page<RoomResponse> getRooms(RoomCriteriaRequest criteriaRequest, Pageable pageable) {
        log.info("ActionLog.getRooms start");

        Specification<RoomEntity> spec = RoomSpecification.getRoomByCriteria(criteriaRequest);
        Page<RoomEntity> rooms = roomRepository.findAll(spec, pageable);

        log.info("ActionLog.getRooms end");

        return rooms.map(i->roomMapper.mapToResponse(i,criteriaRequest.getKey()));
    }

    public RoomResponseFindById roomResponseFindById(Long id,LangType key) {

        log.info("ActionLog.roomResponseFindById start with id#" + id);

        RoomEntity roomEntity = roomRepository.findById(id).orElseThrow(() ->
                new RoomNotFoundException(HttpStatus.NOT_FOUND.name(), "Room not found"));
        roomEntity.setImages(roomEntity.getImages().stream().filter(i -> !i.isDeleted()).toList());
        log.info("ActionLog.roomResponseFindById end");

        return roomMapper.mapToRoomResponseFindById(roomEntity,key);

    }


    public RoomResponse update(Long id, RoomRequest roomRequest) {
        log.info("ActionLog.updateRoom start with id#" + id);

        RoomEntity roomEntity = roomRepository.findById(id).orElseThrow(() ->
                new RoomNotFoundException(HttpStatus.NOT_FOUND.name(), "Room not found"));
//        roomMapper.mapForUpdate(roomEntity, roomRequest);
        roomEntity = roomRepository.save(roomEntity);

        log.info("ActionLog.updateRoom end");

        return roomMapper.mapToResponse(roomEntity,LangType.AZ);
    }

    public void softDelete(Long id) {
        roomRepository.softDelete(id);
    }


}
