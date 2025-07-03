package com.travelify.travelify.controller;

import com.travelify.travelify.dto.request.ApiResponse;
import com.travelify.travelify.entity.Hotel;
import com.travelify.travelify.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ApiResponse<List<Hotel>> getHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return ApiResponse.<List<Hotel>>builder()
                .result(hotels)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        return ApiResponse.<Hotel>builder()
                .result(hotel)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<Hotel>> searchHotels(@RequestParam String q) {
        List<Hotel> hotels = hotelService.searchHotels(q);
        return ApiResponse.<List<Hotel>>builder()
                .result(hotels)
                .build();
    }

    @PostMapping
    public ApiResponse<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel savedHotel = hotelService.saveHotel(hotel);
        return ApiResponse.<Hotel>builder()
                .result(savedHotel)
                .build();
    }
}