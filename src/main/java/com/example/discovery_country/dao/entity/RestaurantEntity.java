package com.example.discovery_country.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    String contact;

    @ManyToOne
    @JoinColumn(nullable = false)
    RegionEntity region;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<ImageEntity> images;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<ReviewEntity> reviews;
    double rating;
    String mapUrl;
    String menuUrl;
    long viewed;
    Long likeCount;
    Double averageRating = 0.0;
    Integer ratingCount = 0;

    boolean status;


}
