package com.travelify.travelify.service;

import com.travelify.travelify.entity.Room;
import com.travelify.travelify.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private  final RoomRepository roomRepository;

    public List<Room> getRoomsByHotelId(Long hotelId){
        return roomRepository.findByHotelId(hotelId);
    }
}
