package com.travelify.travelify.repository;

import com.travelify.travelify.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    Optional<Booking> findByPaymentIntentId(String paymentIntentId);
}