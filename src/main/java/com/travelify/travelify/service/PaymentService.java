package com.travelify.travelify.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    private BookingService bookingService;

    public Map<String, String> createPaymentIntent(Long amount, String currency, String description, String userId, String bookingId) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency(currency)
                .setDescription(description)
                .putMetadata("userId", userId)
                .putMetadata("bookingId", bookingId)
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", paymentIntent.getClientSecret());
        response.put("paymentIntentId", paymentIntent.getId());

        return response;
    }

    public PaymentIntent confirmPayment(String paymentIntentId) throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        paymentIntent = paymentIntent.confirm();
        if ("succeeded".equals(paymentIntent.getStatus())) {
            bookingService.confirmBooking(paymentIntentId);
        }
        return paymentIntent;
    }

    public PaymentIntent getPaymentStatus(String paymentIntentId) throws StripeException {
        return PaymentIntent.retrieve(paymentIntentId);
    }

}
