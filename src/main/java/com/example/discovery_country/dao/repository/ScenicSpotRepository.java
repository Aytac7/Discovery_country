package com.example.discovery_country.dao.repository;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScenicSpotRepository extends JpaRepository<ScenicSpotEntity, Long> ,
        PagingAndSortingRepository<ScenicSpotEntity, Long>, JpaSpecificationExecutor<ScenicSpotEntity> {
    @Modifying
    @Transactional
    @Query("UPDATE ScenicSpotEntity a SET a.checkStatus = true WHERE a.id = :id")
    void updateStatus(Long id);

    List<ScenicSpotEntity> findScenicSpotEntitiesByStatusFalseAndCheckStatusFalse();

    @Modifying
    @Transactional
    @Query("UPDATE ScenicSpotEntity a SET a.status = true WHERE a.id = :id")
    void softDelete(Long id);


    Optional<ScenicSpotEntity> findByIdAndCheckStatusTrueAndStatusFalse(long id);

}
