package com.example.discovery_country.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomReservationResponse {

    String name;
    LocalDate entryDate;
    byte totalDay;
    double totalAmount;
    LocalDate exitDate;
    byte numberOfGuests;
    UserResponse user;
}
