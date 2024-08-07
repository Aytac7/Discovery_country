package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.criteria.RestaurantCriteriaRequest;
import com.example.discovery_country.model.dto.request.RegionRequest;
import com.example.discovery_country.model.dto.request.RestaurantRequest;
import com.example.discovery_country.model.dto.request.RestaurantRequestForUpdate;
import com.example.discovery_country.model.dto.response.RegionResponse;
import com.example.discovery_country.model.dto.response.RegionResponseForFindById;
import com.example.discovery_country.model.dto.response.RestaurantResponse;
import com.example.discovery_country.model.dto.response.RestaurantResponseForFindById;
import com.example.discovery_country.service.RegionService;
import com.example.discovery_country.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurant")
@Slf4j
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService service;


    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody RestaurantRequest request) {
        log.info("Controller.createRestaurant start");
        RestaurantResponse response = service.createRestaurant(request);
        log.info("Controller.createRestaurant end");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantResponse>> getRestaurants(RestaurantCriteriaRequest criteriaRequest,
                                                                   Pageable pageable) {
        log.info("Controller.getRestaurants start");
        Page<RestaurantResponse> responses = service.getRestaurants(criteriaRequest, pageable);
        log.info("Controller.getRestaurants end");
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(
            @PathVariable Long id,
            @RequestBody RestaurantRequestForUpdate request) {
        log.info("Controller.updateZone start with id#" + id);
        service.updateRestaurant(id, request);
        log.info("Controller.updateZone end");
        return ResponseEntity.ok("Update successful");
    }

    @DeleteMapping("/{id}")
    public void softDelete(@PathVariable Long id) {
        log.info("Controller.softDelete start with id#" + id);
        service.softDelete(id);
        log.info("Controller.softDelete end");
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseForFindById> findRestaurantById(
            @PathVariable Long id) {
        log.info("Controller.findRestaurantById start with id#" + id);
        RestaurantResponseForFindById restaurant = service.findRestaurantById(id);
        log.info("Controller.findRestaurantById end");
        return ResponseEntity.ok(restaurant);
    }

}