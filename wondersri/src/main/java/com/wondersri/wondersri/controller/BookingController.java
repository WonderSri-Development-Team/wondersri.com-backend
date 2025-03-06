package com.wondersri.wondersri.controller;
import com.wondersri.wondersri.dto.request.BookingSaveRequestDTO;
import com.wondersri.wondersri.dto.response.AvailableSlotsResponseDTO;
import com.wondersri.wondersri.dto.response.BookingAllDetailDTO;
import com.wondersri.wondersri.dto.response.GetBookingByCodeResponseDTO;
import com.wondersri.wondersri.entity.Booking;
import com.wondersri.wondersri.exception.ResourceNotFoundException;
import com.wondersri.wondersri.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @PostMapping("/save")
    public ResponseEntity<?> saveBooking(@RequestBody BookingSaveRequestDTO bookingRequestDTO) {
        try {
            Booking savedBooking = bookingService.saveBooking(bookingRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/{bookingCode}")
    public ResponseEntity<?> getBookingByCode(@PathVariable String bookingCode) {
        try {
             GetBookingByCodeResponseDTO booking = bookingService.getBookingByCode(bookingCode);
            return ResponseEntity.ok(booking);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/available-slots")
    public ResponseEntity<List<AvailableSlotsResponseDTO>> getAvailableSlots() {
        try {
            List<AvailableSlotsResponseDTO> availableSlots = bookingService.getAvailableSlots();
            return ResponseEntity.ok(availableSlots);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/all-bookings")
    public ResponseEntity<List<BookingAllDetailDTO>> getAllBookings() {
        try {
            List<BookingAllDetailDTO> bookings = bookingService.getAllBookings();
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Other endpoints (getAllBookings, getBookingByCode, deleteBooking, etc.)
}