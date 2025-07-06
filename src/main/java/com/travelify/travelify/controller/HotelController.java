package com.travelify.travelify.controller;

import com.travelify.travelify.dto.request.ApiResponse;
import com.travelify.travelify.dto.request.HotelCreateRequest;
import com.travelify.travelify.dto.request.HotelUpdateRequest;
import com.travelify.travelify.dto.response.HotelResponse;
import com.travelify.travelify.service.HotelService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelController {
    HotelService hotelService;

    @GetMapping
    public ApiResponse<List<HotelResponse>> getHotels() {
        List<HotelResponse> hotels = hotelService.getAllHotels();
        return ApiResponse.<List<HotelResponse>>builder()
                .result(hotels)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<HotelResponse> getHotelById(@PathVariable Long id) {
        HotelResponse hotel = hotelService.getHotelById(id);
        return ApiResponse.<HotelResponse>builder()
                .result(hotel)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<HotelResponse>> searchHotels(@RequestParam String q) {
        List<HotelResponse> hotels = hotelService.searchHotels(q);
        return ApiResponse.<List<HotelResponse>>builder()
                .result(hotels)
                .build();
    }

    // ADMIN ONLY - Cần JWT token và role ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<HotelResponse> createHotel(@RequestBody @Valid HotelCreateRequest request) {
        HotelResponse savedHotel = hotelService.createHotel(request);
        return ApiResponse.<HotelResponse>builder()
                .result(savedHotel)
                .build();
    }

    // ADMIN ONLY - Cần JWT token và role ADMIN
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<HotelResponse> updateHotel(@PathVariable Long id, @RequestBody @Valid HotelUpdateRequest request) {
        HotelResponse updatedHotel = hotelService.updateHotel(id, request);
        return ApiResponse.<HotelResponse>builder()
                .result(updatedHotel)
                .build();
    }

    // ADMIN ONLY - Cần JWT token và role ADMIN
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<String> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ApiResponse.<String>builder()
                .result("Hotel has been deleted")
                .build();
    }
}