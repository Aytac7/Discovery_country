package com.example.discovery_country.model.dto.request;

import com.example.discovery_country.enums.LangType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityRequest {



    //NotBlank(message = "can't be blank")
    Map<LangType, String> name;

    //@NotBlank(message = "can't be blank")
    double price;

    String mapUrl;

   // @NotBlank(message = "can't be blank")
    double latitude;

   // @NotBlank(message = "can't be blank")
    double longitude;

   // @NotBlank(message = "can't be blank")
   Map<LangType, String> description;


    ///@NotBlank(message = "can't be blank")
    LocalDateTime startDate;

   // @NotBlank(message = "can't be blank")
    LocalDateTime endDate;

   // @NotBlank(message = "can't be blank")
    LocalDateTime registrationDeadline;

    //@NotBlank(message = "can't be blank")
    String contact;

   // @NotBlank(message = "can't be blank")
   Map<LangType, String> requirements;

    //@NotBlank(message = "can't be blank")
    Integer numberOfPeople;

    List<Long> imageIds;
    Long activityCategoryId;

}



