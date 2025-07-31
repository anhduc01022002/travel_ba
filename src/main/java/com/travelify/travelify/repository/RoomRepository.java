package com.travelify.travelify.repository;

import com.travelify.travelify.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long>{
    List<Room> findByHotelId(Long hotelId);
}
