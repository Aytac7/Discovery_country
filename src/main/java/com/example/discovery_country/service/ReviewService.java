package com.example.discovery_country.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.discovery_country.dao.entity.ReviewEntity;
import com.example.discovery_country.dao.repository.ReviewRepository;
import com.example.discovery_country.mapper.ReviewMapper;
import com.example.discovery_country.model.dto.request.ReviewRequestForHomeHotel;
import com.example.discovery_country.model.dto.request.ReviewRequestForRestaurant;
import com.example.discovery_country.model.dto.request.ReviewRequestForScenicSpots;
import com.example.discovery_country.model.dto.response.ReviewResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    private final AmazonS3 s3Client;
    @Value("${cloud.aws.s3.bucket.name}")
    private String bucketName;
//    public String addPhoto(MultipartFile file) {
//        log.info("ActionLog.addPhoto start");
//        String uploadDir = "C:\\Users\\pabdu\\Desktop\\photos\\";
//        File uploadDirFile = new File(uploadDir);
//        if (!uploadDirFile.exists())
//            uploadDirFile.mkdirs();
//
//        String filePath = uploadDir + file.getOriginalFilename();
//        try {
//            file.transferTo(new File(filePath));
//        } catch (IOException e) {
//            throw new RuntimeException("FILE_COULD_NOT_BE_ADDED");
//        }
//        return filePath;
//
//    }
    public String uploadFile(MultipartFile multipartFile) {
        String filePath;
        String fileUrl;
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getSize());
            filePath = "reviewImages/"+ multipartFile.getOriginalFilename();
            s3Client.putObject(bucketName, filePath,
                    multipartFile.getInputStream(), objectMetadata);
            s3Client.setObjectAcl(bucketName, filePath, CannedAccessControlList.PublicRead);
            fileUrl = s3Client.getUrl(bucketName, filePath).toString();
        } catch (IOException e) {
            log.error("Error occurred ==> {}", e.getMessage());
            throw new RuntimeException("Error occurred in file upload ==> " + e.getMessage());
        }
        return fileUrl;
    }

    public ReviewResponse createRestaurantReview(ReviewRequestForRestaurant request, MultipartFile file) {
        log.info("ActionLog.createRestaurantReview start");
        ReviewEntity reviewEntity = reviewMapper.mapToEntity(request);
        reviewEntity.setPhotoUrl(uploadFile(file));
        ReviewResponse reviewResponse = reviewMapper.mapToResponse(reviewRepository.save(reviewEntity));
        log.info("ActionLog.createRestaurantReview end");
        return reviewResponse;
    }

    public ReviewResponse createScenicSpotReview(ReviewRequestForScenicSpots request, MultipartFile file) {
        log.info("ActionLog.createScenicSpotReview start");
        ReviewEntity reviewEntity = reviewMapper.mapToEntity(request);
        reviewEntity.setPhotoUrl(uploadFile(file));
        ReviewResponse reviewResponse = reviewMapper.mapToResponse(reviewRepository.save(reviewEntity));
        log.info("ActionLog.createScenicSpotReview end");
        return reviewResponse;
    }
//
//    public ReviewResponse createHomeHotelReview(ReviewRequestForHomeHotel request, MultipartFile file) {
//
//        log.info("ActionLog.createHomeHotelReview start");
//        ReviewEntity reviewEntity = reviewMapper.mapToEntity(request);
//        reviewEntity.setPhotoUrl(uploadFile(file));
//
//        ReviewResponse reviewResponse = reviewMapper.mapToResponse(reviewRepository.save(reviewEntity));
//        log.info("ActionLog.createHomeHotelReview end");
//        return reviewResponse;
//    }


    public ReviewResponse createHomeHotelReview(String reviewRequestJson, MultipartFile file) {
        log.info("ActionLog.createHomeHotelReview start");

        ReviewRequestForHomeHotel reviewRequest;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            reviewRequest = objectMapper.readValue(reviewRequestJson, ReviewRequestForHomeHotel.class);
        } catch (IOException e) {
            log.error("Error parsing reviewRequest JSON", e);
            throw new IllegalArgumentException("Invalid review request format");
        }

        ReviewEntity reviewEntity = reviewMapper.mapToEntity(reviewRequest);
        reviewEntity.setPhotoUrl(uploadFile(file));

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
