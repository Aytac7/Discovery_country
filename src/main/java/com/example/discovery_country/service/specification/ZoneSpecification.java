package com.example.discovery_country.service.specification;

import com.example.discovery_country.dao.entity.ZoneEntity;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ZoneSpecification implements Specification<ZoneEntity> {

    public static Specification<ZoneEntity> getZoneByCriteria(CriteriaRequestForName criteriaRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteriaRequest.getName() != null && !criteriaRequest.getName().isEmpty()) {
                Predicate namePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(
                                criteriaBuilder.function(
                                        "jsonb_extract_path_text",
                                        String.class,
                                        root.get("name"),
                                        criteriaBuilder.literal(criteriaRequest.getKey().name())
                                )
                        ),
                        "%" + criteriaRequest.getName().toLowerCase() + "%"
                );

                predicates.add(namePredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Predicate toPredicate(Root<ZoneEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
