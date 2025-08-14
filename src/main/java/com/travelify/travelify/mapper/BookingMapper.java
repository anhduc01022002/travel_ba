package com.travelify.travelify.mapper;

import com.travelify.travelify.dto.request.BookingCreateRequest;
import com.travelify.travelify.dto.response.BookingResponse;
import com.travelify.travelify.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    Booking toBooking(BookingCreateRequest request);

    @Mapping(source = "user.email", target = "userEmail")
    BookingResponse toBookingResponse(Booking booking);
}