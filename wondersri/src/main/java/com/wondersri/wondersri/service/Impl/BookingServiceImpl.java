package com.wondersri.wondersri.service.Impl;
import com.wondersri.wondersri.Util.CodeGenerator;
import com.wondersri.wondersri.dto.request.BookingSaveRequestDTO;
import com.wondersri.wondersri.dto.response.GetBookingByCodeResponseDTO;
import com.wondersri.wondersri.entity.Booking;
import com.wondersri.wondersri.entity.Boat;
import com.wondersri.wondersri.Enum.TimeSlot;
import com.wondersri.wondersri.exception.ResourceNotFoundException;
import com.wondersri.wondersri.repo.BoatRepository;
import com.wondersri.wondersri.repo.BookingRepository;
import com.wondersri.wondersri.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BoatRepository boatRepository;
    @Autowired
    private CodeGenerator codeGenerator;


    private boolean isTimeSlotAvailable(LocalDate date, TimeSlot timeSlot) {
        return bookingRepository.findByBookingDateAndTimeSlot(date, timeSlot).isEmpty();
    }
    @Override
    public Booking saveBooking(BookingSaveRequestDTO bookingSaveRequestDTO) {
        Boat boat = boatRepository.findById(bookingSaveRequestDTO.getBoatId())
                .orElseThrow(() -> new IllegalArgumentException("Boat not found with ID: " + bookingSaveRequestDTO.getBoatId()));

        // Validate the time slot availability
        if (!isTimeSlotAvailable(bookingSaveRequestDTO.getBookingDate(), bookingSaveRequestDTO.getTimeSlot())) {
            throw new IllegalArgumentException("Time slot " + bookingSaveRequestDTO.getTimeSlot() + " is already booked for " + bookingSaveRequestDTO.getBookingDate());
        }

        // Convert BookingRequestDTO to Booking entity
        Booking booking = new Booking();
        booking.setBoat(boat); // Set the Boat entity
        booking.setUserName(bookingSaveRequestDTO.getUserName());
        booking.setUserEmail(bookingSaveRequestDTO.getUserEmail());
        booking.setUserPhone(bookingSaveRequestDTO.getUserPhone());
        booking.setBookingDate(bookingSaveRequestDTO.getBookingDate());
        booking.setTimeSlot(bookingSaveRequestDTO.getTimeSlot());

        // Generate a unique booking code
        booking.setBookingCode(codeGenerator.generateUniqueBookingCode());

        // Set default status
        booking.setStatus("confirmed");

        // Save the booking
        return bookingRepository.save(booking);
    }

    @Override
    public GetBookingByCodeResponseDTO getBookingByCode(String bookingCode) {
        Booking booking = bookingRepository.findByBookingCode(bookingCode)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with code: " + bookingCode));

        // Assuming Boat entity has a getName() method
        String boatName = booking.getBoat() != null ? booking.getBoat().getName() : "Unknown Boat";

        return new GetBookingByCodeResponseDTO(
                booking.getId(),
                boatName,
                booking.getUserName(),
                booking.getUserEmail(),
                booking.getUserPhone(),
                booking.getBookingDate(),
                booking.getTimeSlot(),
                booking.getBookingCode(),
                booking.getStatus()
        );
    }
}