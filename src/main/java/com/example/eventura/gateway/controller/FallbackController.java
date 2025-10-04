package com.example.eventura.gateway.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FallbackController {

    @GetMapping("/fallback/user")
    public ResponseEntity<Map<String, String>> userFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "User Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/fallback/catalog")
    public ResponseEntity<Map<String, String>> catalogFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "Catalog Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/fallback/shows")
    public ResponseEntity<Map<String, String>> showsFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "Show Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/fallback/bookings")
    public ResponseEntity<Map<String, String>> bookingsFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "Booking Service (bookings) is currently unavailable. Please try again later."));
    }

    @GetMapping("/fallback/locks")
    public ResponseEntity<Map<String, String>> locksFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "Booking Service (locks) is currently unavailable. Please try again later."));
    }

    @GetMapping("/fallback/payments")
    public ResponseEntity<Map<String, String>> paymentsFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "Payment Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/fallback/notifications")
    public ResponseEntity<Map<String, String>> notificationsFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "Notification Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/fallback/audit")
    public ResponseEntity<Map<String, String>> auditFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "Audit Service is currently unavailable. Please try again later."));
    }
}
