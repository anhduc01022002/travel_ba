package com.travelify.travelify.controller;

import com.travelify.travelify.dto.request.ApiResponse;
import com.travelify.travelify.entity.Room;
import com.travelify.travelify.service.RoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomController {
    RoomService roomService;

    @GetMapping("/{id}/rooms")
    public ApiResponse<List<Room>> getRoomsByHotelId(@PathVariable Long id){
        List<Room> rooms = roomService.getRoomsByHotelId(id);
        return ApiResponse.<List<Room>>builder()
                .result(rooms)
                .build();
    }

    @PostMapping("/{id}/rooms")
    public ApiResponse<Room> createRoom(@PathVariable Long id, @RequestBody Room room) {
        room.setHotelId(id); // Đặt hotelId cho phòng
        Room savedRoom = roomService.saveRoom(room);
        return ApiResponse.<Room>builder()
                .result(savedRoom)
                .build();
    }
}
