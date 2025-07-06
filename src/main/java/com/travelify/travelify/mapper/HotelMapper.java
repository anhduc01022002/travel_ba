package com.travelify.travelify.mapper;

import com.travelify.travelify.dto.request.HotelCreateRequest;
import com.travelify.travelify.dto.request.HotelUpdateRequest;
import com.travelify.travelify.dto.response.HotelResponse;
import com.travelify.travelify.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HotelMapper {
    Hotel toHotel(HotelCreateRequest request);
    HotelResponse toHotelResponse(Hotel hotel);
    void updateHotel(@MappingTarget Hotel hotel, HotelUpdateRequest request);
}