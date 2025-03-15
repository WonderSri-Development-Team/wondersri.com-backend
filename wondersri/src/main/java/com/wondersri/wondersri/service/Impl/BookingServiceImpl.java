package com.wondersri.wondersri.service.Impl;

import com.wondersri.wondersri.Util.CodeGenerator;
import com.wondersri.wondersri.dto.request.BookingSaveRequestDTO;
import com.wondersri.wondersri.dto.response.AvailableSlotsResponseDTO;
import com.wondersri.wondersri.dto.response.BookingAllDetailDTO;
import com.wondersri.wondersri.dto.response.GetBookingByCodeResponseDTO;
import com.wondersri.wondersri.entity.Booking;
import com.wondersri.wondersri.entity.Boat;
import com.wondersri.wondersri.Enum.TimeSlot;
import com.wondersri.wondersri.exception.ResourceNotFoundException;
import com.wondersri.wondersri.repo.BoatRepository;
import com.wondersri.wondersri.repo.BookingRepository;
import com.wondersri.wondersri.service.BookingService;
import com.wondersri.wondersri.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BoatRepository boatRepository;
    @Autowired
    private CodeGenerator codeGenerator;
    @Autowired
    private EmailService emailService;
    private static final int TWO_MONTHS_IN_DAYS = 60;

    private boolean isTimeSlotAvailable(LocalDate date, TimeSlot timeSlot) {
        return bookingRepository.findByBookingDateAndTimeSlot(date, timeSlot).isEmpty();
    }

    @Override
    public Booking saveBooking(BookingSaveRequestDTO bookingSaveRequestDTO) {
        Boat boat = boatRepository.findById(bookingSaveRequestDTO.getBoatId())
                .orElseThrow(() -> new IllegalArgumentException("Boat not found with ID: " + bookingSaveRequestDTO.getBoatId()));

        if (!isTimeSlotAvailable(bookingSaveRequestDTO.getBookingDate(), bookingSaveRequestDTO.getTimeSlot())) {
            throw new IllegalArgumentException("Time slot " + bookingSaveRequestDTO.getTimeSlot() + " is already booked for " + bookingSaveRequestDTO.getBookingDate());
        }

        Booking booking = new Booking();
        booking.setBoat(boat);
        booking.setUserName(bookingSaveRequestDTO.getUserName());
        booking.setUserEmail(bookingSaveRequestDTO.getUserEmail());
        booking.setUserPhone(bookingSaveRequestDTO.getUserPhone());
        booking.setBookingDate(bookingSaveRequestDTO.getBookingDate());
        booking.setTimeSlot(bookingSaveRequestDTO.getTimeSlot());
        String bookingCode = codeGenerator.generateUniqueBookingCode();
        booking.setBookingCode(bookingCode);
        booking.setStatus("confirmed");

        bookingRepository.save(booking);

        try {
            emailService.sendBookingConfirmationEmail(
                    booking.getUserEmail(),
                    booking.getBookingCode(),
                    booking.getUserName(),
                    boat.getName(),
                    boat.getLocation(),
                    booking.getBookingDate().toString(),
                    booking.getTimeSlot().toString()
            );
        } catch (MailAuthenticationException e) {
            System.err.println("Email authentication failed: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error sending email: " + e.getMessage());
        }
        return booking;
    }

    @Override
    public GetBookingByCodeResponseDTO getBookingByCode(String bookingCode) {
        Booking booking = bookingRepository.findByBookingCode(bookingCode)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with code: " + bookingCode));

        // Assuming Boat entity has a getName() method
        String boatName = booking.getBoat() != null ? booking.getBoat().getName() : "Unknown Boat";

        return new GetBookingByCodeResponseDTO(
                boatName,
                booking.getUserName(),
                booking.getUserEmail(),
                booking.getUserPhone(),
                booking.getBookingDate(),
<<<<<<< Updated upstream
<<<<<<< Updated upstream
                booking.getTimeSlot(),
                booking.getBookingCode()
=======
                booking.getTimeSlot()
>>>>>>> Stashed changes
=======
                booking.getTimeSlot()
>>>>>>> Stashed changes
        );
    }

    @Override
    public List<AvailableSlotsResponseDTO> getAvailableSlots() {
        List<AvailableSlotsResponseDTO> availableSlots = new ArrayList<>();
        LocalDate tomorrow = LocalDate.now().plusDays(1); // Start from tomorrow

        // Get all possible time slots
        List<TimeSlot> allTimeSlots = Arrays.asList(TimeSlot.values());

        // Check availability for the next 2 months (60 days)
        for (int i = 0; i < TWO_MONTHS_IN_DAYS; i++) {
            LocalDate date = tomorrow.plusDays(i);
            List<Booking> bookingsForDate = bookingRepository.findByBookingDate(date);

            // Get booked time slots for this date
            List<TimeSlot> bookedSlots = bookingsForDate.stream()
                    .map(Booking::getTimeSlot)
                    .collect(Collectors.toList());

            // Filter out booked slots to get available slots
            List<TimeSlot> availableTimeSlots = allTimeSlots.stream()
                    .filter(slot -> !bookedSlots.contains(slot))
                    .collect(Collectors.toList());

            availableSlots.add(new AvailableSlotsResponseDTO(date, availableTimeSlots));
        }

        return availableSlots;
    }

    @Override
    public List<BookingAllDetailDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(booking -> {
                    String boatName = booking.getBoat() != null ? booking.getBoat().getName() : "Unknown Boat";
                    return new BookingAllDetailDTO(
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
                })
                .collect(Collectors.toList());
    }
}