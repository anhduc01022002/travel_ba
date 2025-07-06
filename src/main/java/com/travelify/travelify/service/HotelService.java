package com.travelify.travelify.service;

import com.travelify.travelify.dto.request.HotelCreateRequest;
import com.travelify.travelify.dto.request.HotelUpdateRequest;
import com.travelify.travelify.dto.response.HotelResponse;
import com.travelify.travelify.entity.Hotel;
import com.travelify.travelify.exception.AppException;
import com.travelify.travelify.exception.ErrorCode;
import com.travelify.travelify.mapper.HotelMapper;
import com.travelify.travelify.repository.HotelRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelService {
    HotelRepository hotelRepository;
    HotelMapper hotelMapper;

    public List<HotelResponse> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::toHotelResponse)
                .toList();
    }

    public HotelResponse getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_FOUND));
        return hotelMapper.toHotelResponse(hotel);
    }

    public List<HotelResponse> searchHotels(String query) {
        return hotelRepository.findByNameContainingIgnoreCase(query).stream()
                .map(hotelMapper::toHotelResponse)
                .toList();
    }

    public HotelResponse createHotel(HotelCreateRequest request) {
        Hotel hotel = hotelMapper.toHotel(request);
        Hotel savedHotel = hotelRepository.save(hotel);
        return hotelMapper.toHotelResponse(savedHotel);
    }

    public HotelResponse updateHotel(Long id, HotelUpdateRequest request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_FOUND));

        hotelMapper.updateHotel(hotel, request);
        Hotel updatedHotel = hotelRepository.save(hotel);
        return hotelMapper.toHotelResponse(updatedHotel);
    }

    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new AppException(ErrorCode.HOTEL_NOT_FOUND);
        }
        hotelRepository.deleteById(id);
    }
}