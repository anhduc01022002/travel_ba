package com.travelify.travelify.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelCreateRequest {
    @NotBlank(message = "HOTEL_NAME_REQUIRED")
    String name;

    String imageUrl;

    @NotNull(message = "HOTEL_RATE_REQUIRED")
    @Positive(message = "HOTEL_RATE_INVALID")
    Double rate;

    @NotNull(message = "HOTEL_PRICE_REQUIRED")
    @Positive(message = "HOTEL_PRICE_INVALID")
    Double price;

    @NotBlank(message = "HOTEL_ADDRESS_REQUIRED")
    String address;

    List<String> amenities;

    String description;

    Boolean isAvailable = true;
}