package com.travelify.travelify.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelUpdateRequest {
    String name;
    String imageUrl;

    @Positive(message = "HOTEL_RATE_INVALID")
    Double rate;

    @Positive(message = "HOTEL_PRICE_INVALID")
    Double price;

    String address;
    List<String> amenities;
    String description;
    Boolean isAvailable;
}