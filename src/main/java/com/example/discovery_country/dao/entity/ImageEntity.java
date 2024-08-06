package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String url;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    HotelRoomsEntity hotelRoom;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    HomeHotelEntity homeHotel;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    ActivityEntity activity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    ScenicSpotEntity scenicSpot;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    RestaurantEntity restaurant;

    Boolean deleted = false;


}
