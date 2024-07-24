package com.example.discovery_country.model.dto.response;

import com.example.discovery_country.enums.RoomType;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelRoomsResponse {

    String name;
    double price;
    byte roomNumber;
    byte roomCount;
    RoomType roomType;
    boolean available;
    Long homeHotelId;
    List<Long> imageIds;
    List<Long> roomReservationIds;

    @Column(columnDefinition = "TEXT")
    String amenities;//json b

}