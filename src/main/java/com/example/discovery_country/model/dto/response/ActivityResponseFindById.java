package com.example.discovery_country.model.dto.response;

import com.example.discovery_country.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityResponseFindById {


    @NotBlank(message = "can't be blank")
    String name;

    @NotBlank(message = "can't be blank")
    double price;

    String mapUrl;

//    @NotBlank(message = "can't be blank")
//    double latitude;
//
//    @NotBlank(message = "can't be blank")
//    double longitude;

    @NotBlank(message = "can't be blank")
    String description;

    @NotBlank(message = "can't be blank")
    LocalDateTime startDate;

    @NotBlank(message = "can't be blank")
    LocalDateTime endDate;

    @NotBlank(message = "can't be blank")
    LocalDateTime registrationDeadline;

    @NotBlank(message = "can't be blank")
    String contact;

    @NotBlank(message = "can't be blank")
    String requirements;

    @NotBlank(message = "can't be blank")
    Integer numberOfPeople;

    List<ImageResponseForActivity> images;
    ActivityCategoryResponseForActivity activityCategory;

}
