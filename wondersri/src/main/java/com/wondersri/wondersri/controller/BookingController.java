package com.wondersri.wondersri.controller;

import com.wondersri.wondersri.dto.request.BookingSaveRequestDTO;
import com.wondersri.wondersri.dto.response.AvailableSlotsResponseDTO;
import com.wondersri.wondersri.dto.response.BookingAllDetailDTO;
import com.wondersri.wondersri.dto.response.GetBookingByCodeResponseDTO;
import com.wondersri.wondersri.dto.response.GetBookingByEmailResponseDTO;
import com.wondersri.wondersri.exception.ResourceNotFoundException;
import com.wondersri.wondersri.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "https://wondersri.com")
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/save-booking", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> saveBooking(@RequestBody BookingSaveRequestDTO bookingRequestDTO) {
        logger.info("Received booking request: {}", bookingRequestDTO);
        try {
            bookingService.saveBooking(bookingRequestDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Booking Confirmed");
            response.put("status", "success");
            logger.info("Booking saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            error.put("status", "not_found");
            logger.error("Resource not found: {}", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            error.put("status", "bad_request");
            logger.error("Bad request: {}", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error saving booking: " + e.getMessage());
            error.put("status", "server_error");
            logger.error("Server error: {}", e.getMessage(), e);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/booking-by-code/{bookingCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBookingByCode(@PathVariable String bookingCode) {
        try {
            GetBookingByCodeResponseDTO booking = bookingService.getBookingByCode(bookingCode);
            return ResponseEntity.ok(booking);
        } catch (ResourceNotFoundException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/booking-by-email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBookingByEmail(@PathVariable String email) {
        try {
            GetBookingByEmailResponseDTO booking = bookingService.getBookingByemail(email);
            return ResponseEntity.ok(booking);
        } catch (ResourceNotFoundException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/available-slots", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAvailableSlots() {
        try {
            List<AvailableSlotsResponseDTO> availableSlots = bookingService.getAvailableSlots();
            return ResponseEntity.ok(availableSlots);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error fetching available slots: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all-bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllBookings() {
        try {
            List<BookingAllDetailDTO> bookings = bookingService.getAllBookings();
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error fetching bookings: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}