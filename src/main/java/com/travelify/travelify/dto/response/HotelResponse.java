package com.travelify.travelify.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelResponse {
    Long id;
    String name;
    String imageUrl;
    Double rate;
    Double price;
    String address;
    List<String> amenities;
    String description;
    Boolean isAvailable;
}