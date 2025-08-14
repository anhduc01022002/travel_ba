package com.travelify.travelify.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingCreateRequest {
    String userId;
    Long hotelId;
    Long roomId;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Long amount;
    String currency = "vnd";
}