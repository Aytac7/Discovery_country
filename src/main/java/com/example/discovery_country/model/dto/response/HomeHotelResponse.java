package com.example.discovery_country.model.dto.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeHotelResponse {

    Long id;
    String name;
    String address;

    double price;
    Long viewed;
    ImageResponseForRelations image;

}
