package com.travelify.travelify.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    String id;
    String userEmail;
    Long hotelId;
    Long roomId;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Long amount;
    String currency;
    String status;
    LocalDateTime createdAt;
}