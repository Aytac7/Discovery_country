package com.example.discovery_country.model.dto.response;

import com.example.discovery_country.dao.entity.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeHotelResponseFindById {


    Long id;
    String name;
    @Column(columnDefinition = "TEXT")
    String description;
    String address;
    String contact;
    String mapUrl;
    double price;
    String type;
    Long viewed;
    Long likeCount;
    Double averageRating ;
    Integer ratingCount ;


    List<RoomsEntity> hotelRooms;

    List<ImageEntity> images;

    List<ReviewEntity> reviews;

    List<RoomReservationEntity> roomReservations;

    RegionEntity region;
}
