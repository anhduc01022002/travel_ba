package com.travelify.travelify.service;

import com.travelify.travelify.entity.Hotel;
import com.travelify.travelify.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
    }

    public List<Hotel> searchHotels(String query) {
        return hotelRepository.findByNameContainingIgnoreCase(query);
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
}