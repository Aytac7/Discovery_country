package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ReviewEntity;
import com.example.discovery_country.dao.repository.ReviewRepository;
import com.example.discovery_country.mapper.ReviewMapper;
import com.example.discovery_country.model.dto.request.ReviewRequestForHomeHotel;
import com.example.discovery_country.model.dto.request.ReviewRequestForRestaurant;
import com.example.discovery_country.model.dto.request.ReviewRequestForScenicSpots;
import com.example.discovery_country.model.dto.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public String addPhoto(MultipartFile file) {
        log.info("ActionLog.addPhoto start");
        String uploadDir = "C:\\Users\\pabdu\\Desktop\\photos\\";
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists())
            uploadDirFile.mkdirs();

        String filePath = uploadDir + file.getOriginalFilename();
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("FILE_COULD_NOT_BE_ADDED");
        }
        return filePath;

    }

    public ReviewResponse createRestaurantReview(ReviewRequestForRestaurant request, MultipartFile file) {
        log.info("ActionLog.createRestaurantReview start");
        ReviewEntity reviewEntity = reviewMapper.mapToEntity(request);
        reviewEntity.setPhotoUrl(addPhoto(file));
        ReviewResponse reviewResponse = reviewMapper.mapToResponse(reviewRepository.save(reviewEntity));
        log.info("ActionLog.createRestaurantReview end");
        return reviewResponse;
    }

    public ReviewResponse createScenicSpotReview(ReviewRequestForScenicSpots request, MultipartFile file) {
        log.info("ActionLog.createScenicSpotReview start");
        ReviewEntity reviewEntity = reviewMapper.mapToEntity(request);
        reviewEntity.setPhotoUrl(addPhoto(file));
        ReviewResponse reviewResponse = reviewMapper.mapToResponse(reviewRepository.save(reviewEntity));
        log.info("ActionLog.createScenicSpotReview end");
        return reviewResponse;
    }

    public ReviewResponse createHomeHotelReview(ReviewRequestForHomeHotel request, MultipartFile file) {

        log.info("ActionLog.createHomeHotelReview start");
        ReviewEntity reviewEntity = reviewMapper.mapToEntity(request);
        reviewEntity.setPhotoUrl(addPhoto(file));

        ReviewResponse reviewResponse = reviewMapper.mapToResponse(reviewRepository.save(reviewEntity));
        log.info("ActionLog.createHomeHotelReview end");
        return reviewResponse;
    }

    public void softDelete(long id) {
        log.info("ActionLog.softDeleteReview start with id#" + id);
        reviewRepository.softDelete(id);
        log.info("ActionLog.softDeleteReview end");

    }
}
