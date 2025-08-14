package com.travelify.travelify.controller;

import com.travelify.travelify.dto.request.ApiResponse;
import com.travelify.travelify.dto.request.BookingCreateRequest;
import com.travelify.travelify.dto.response.BookingResponse;
import com.travelify.travelify.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {
    BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")  // Require authenticated user
    public ApiResponse<BookingResponse> createBooking(@RequestBody BookingCreateRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ApiResponse.<BookingResponse>builder()
                .result(response)
                .build();
    }
}