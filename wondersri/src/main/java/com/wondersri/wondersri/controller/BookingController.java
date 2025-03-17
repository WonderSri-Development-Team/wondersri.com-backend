package com.wondersri.wondersri.controller;

import com.wondersri.wondersri.dto.request.BookingSaveRequestDTO;
import com.wondersri.wondersri.dto.response.AvailableSlotsResponseDTO;
import com.wondersri.wondersri.dto.response.BookingAllDetailDTO;
import com.wondersri.wondersri.dto.response.GetBookingByCodeResponseDTO;
import com.wondersri.wondersri.dto.response.GetBookingByEmailResponseDTO;
import com.wondersri.wondersri.exception.ResourceNotFoundException;
import com.wondersri.wondersri.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/save-booking")
    public ResponseEntity<Map<String, String>> saveBooking(@RequestBody BookingSaveRequestDTO bookingRequestDTO) {
        try {
            bookingService.saveBooking(bookingRequestDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Booking Confirmed");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error saving booking: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/booking-by-code/{bookingCode}")
    public ResponseEntity<?> getBookingByCode(@PathVariable String bookingCode) {
        try {
            GetBookingByCodeResponseDTO booking = bookingService.getBookingByCode(bookingCode);
            return ResponseEntity.ok(booking);
        } catch (ResourceNotFoundException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/booking-by-email/{email}")
    public ResponseEntity<?> getBookingByEmail(@PathVariable String email) {
        try {
            GetBookingByEmailResponseDTO booking = bookingService.getBookingByemail(email);
            return ResponseEntity.ok(booking);
        } catch (ResourceNotFoundException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/available-slots")
    public ResponseEntity<?> getAvailableSlots() {
        try {
            List<AvailableSlotsResponseDTO> availableSlots = bookingService.getAvailableSlots();
            return ResponseEntity.ok(availableSlots);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error fetching available slots: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all-bookings")
    public ResponseEntity<?> getAllBookings() {
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