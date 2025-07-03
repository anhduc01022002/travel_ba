package com.travelify.travelify.repository;

import com.travelify.travelify.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByNameContainingIgnoreCase(String name);
}