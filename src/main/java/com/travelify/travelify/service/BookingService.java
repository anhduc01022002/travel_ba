package com.travelify.travelify.service;

import com.travelify.travelify.dto.request.BookingCreateRequest;
import com.travelify.travelify.dto.response.BookingResponse;
import com.travelify.travelify.entity.Booking;
import com.travelify.travelify.entity.User;
import com.travelify.travelify.exception.AppException;
import com.travelify.travelify.exception.ErrorCode;
import com.travelify.travelify.mapper.BookingMapper;
import com.travelify.travelify.repository.BookingRepository;
import com.travelify.travelify.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {
    BookingRepository bookingRepository;
    UserRepository userRepository;
    BookingMapper bookingMapper;
    JavaMailSender javaMailSender;

    public BookingResponse createBooking(BookingCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Booking booking = bookingMapper.toBooking(request);
        booking.setUser(user);
        booking = bookingRepository.save(booking);

        return bookingMapper.toBookingResponse(booking);
    }

    public void confirmBooking(String paymentIntentId) {
        Booking booking = bookingRepository.findByPaymentIntentId(paymentIntentId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));  // Customize error as needed

        booking.setStatus("CONFIRMED");
        booking.setUpdatedAt(LocalDateTime.now());
        bookingRepository.save(booking);

        sendConfirmationEmail(booking);
    }

    private void sendConfirmationEmail(Booking booking) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(booking.getUser().getEmail());
        message.setSubject("Xác Nhận Đặt Phòng Thành Công");
        message.setText(String.format(
                "Cảm ơn bạn đã đặt phòng!\n\nChi tiết:\n- Khách sạn ID: %d\n- Phòng ID: %d\n- Check-in: %s\n- Check-out: %s\n- Tổng tiền: %d %s\n\nChúc bạn có chuyến đi vui vẻ!",
                booking.getHotelId(), booking.getRoomId(), booking.getCheckInDate(), booking.getCheckOutDate(), booking.getAmount(), booking.getCurrency()
        ));
        javaMailSender.send(message);
    }
}