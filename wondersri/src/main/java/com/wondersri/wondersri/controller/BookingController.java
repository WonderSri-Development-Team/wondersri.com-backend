package com.wondersri.wondersri.controller;
import com.wondersri.wondersri.dto.request.BookingSaveRequestDTO;
import com.wondersri.wondersri.dto.response.AvailableSlotsResponseDTO;
import com.wondersri.wondersri.dto.response.BookingAllDetailDTO;
import com.wondersri.wondersri.dto.response.GetBookingByCodeResponseDTO;
import com.wondersri.wondersri.exception.ResourceNotFoundException;
import com.wondersri.wondersri.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @PostMapping("/Save-booking")
    public ResponseEntity<String> saveBooking(@RequestBody BookingSaveRequestDTO bookingRequestDTO) {
        bookingService.saveBooking(bookingRequestDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>("Booking Confirmed", headers, HttpStatus.CREATED);
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