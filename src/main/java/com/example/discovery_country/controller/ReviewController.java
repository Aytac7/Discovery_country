package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.request.*;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.response.ReviewResponse;
import com.example.discovery_country.model.dto.response.ZoneResponse;
import com.example.discovery_country.service.ReviewService;
import com.example.discovery_country.service.ZoneService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/api/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(
            @RequestPart("file") MultipartFile file) {
        log.info("Controller.uploadFile start");

        String path = reviewService.uploadFile(file);
        log.info("Controller.uploadFile end");

        return path;
    }

        @PostMapping("/home-hotel")
    public ResponseEntity<ReviewResponse> createHomeHotelReview(@Valid @RequestPart("review") String review,
                                                                @RequestPart("photo") MultipartFile photo) {
        log.info("Controller.createHomeHotelReview start");

        ReviewResponse response = reviewService.createHomeHotelReview(review, photo);

        log.info("Controller.createHomeHotelReview end");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/scenic-spot")
    public ResponseEntity<ReviewResponse> createScenicSpotReview(@Valid @RequestPart("review") String review,
                                                                 @RequestPart("photo") MultipartFile photo) {
        log.info("Controller.createScenicSpotReview start");

        ReviewResponse response = reviewService.createScenicSpotReview(review, photo);

        log.info("Controller.createScenicSpotReview end");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


//    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
//    public ResponseEntity<String> uploadFile(
//            @RequestPart("requestDTO") RequestDTO requestDTO,
//            @RequestPart("file") MultipartFile file)
//

    @PostMapping(value = "/restaurant", consumes = {"multipart/form-data"})
    public ResponseEntity<ReviewResponse> createRestaurantReview
            (@Valid @RequestPart("review") String review,
                                                                 @RequestPart("photo") MultipartFile photo) {
        log.info("Controller.createRestaurantReview start");

        ReviewResponse response = reviewService.createRestaurantReview(review, photo);

        log.info("Controller.createRestaurantReview end");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}