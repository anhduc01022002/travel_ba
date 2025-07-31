package com.travelify.travelify.controller;

import com.travelify.travelify.dto.request.ApiResponse;
import com.travelify.travelify.entity.Room;
import com.travelify.travelify.service.RoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
