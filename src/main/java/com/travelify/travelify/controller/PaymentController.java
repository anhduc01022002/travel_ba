package com.travelify.travelify.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.travelify.travelify.dto.request.PaymentRequest;
import com.travelify.travelify.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody PaymentRequest request) {
        try {
            Map<String, String> response = paymentService.createPaymentIntent(
                    request.getAmount(),
                    request.getCurrency() != null ? request.getCurrency() : "usd",
                    request.getDescription(),
                    request.getUserId(),
                    request.getBookingId()
            );
            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/confirm-payment")
    public ResponseEntity<Map<String, Object>> confirmPayment(@RequestBody Map<String, String> request) {
        try {
            String paymentIntentId = request.get("paymentIntentId");
            PaymentIntent paymentIntent = paymentService.confirmPayment(paymentIntentId);

            Map<String, Object> response = Map.of(
                    "status", paymentIntent.getStatus(),
                    "paymentIntentId", paymentIntent.getId()
            );

            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/payment-status/{paymentIntentId}")
    public ResponseEntity<Map<String, Object>> getPaymentStatus(@PathVariable String paymentIntentId) {
        try {
            PaymentIntent paymentIntent = paymentService.getPaymentStatus(paymentIntentId);

            Map<String, Object> response = Map.of(
                    "status", paymentIntent.getStatus(),
                    "amount", paymentIntent.getAmount(),
                    "currency", paymentIntent.getCurrency()
            );

            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
