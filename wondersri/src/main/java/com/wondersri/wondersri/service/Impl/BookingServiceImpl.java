package com.wondersri.wondersri.service.Impl;

import com.wondersri.wondersri.Util.CodeGenerator;
import com.wondersri.wondersri.dto.request.BookingSaveRequestDTO;
import com.wondersri.wondersri.dto.response.AvailableSlotsResponseDTO;
import com.wondersri.wondersri.dto.response.BookingAllDetailDTO;
import com.wondersri.wondersri.dto.response.GetBookingByCodeResponseDTO;
import com.wondersri.wondersri.dto.response.GetBookingByEmailResponseDTO;
import com.wondersri.wondersri.entity.Booking;
import com.wondersri.wondersri.entity.Boat;
import com.wondersri.wondersri.Enum.TimeSlot;
import com.wondersri.wondersri.exception.ResourceNotFoundException;
import com.wondersri.wondersri.repo.BoatRepository;
import com.wondersri.wondersri.repo.BookingRepository;
import com.wondersri.wondersri.service.BookingService;
import com.wondersri.wondersri.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

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
        logger.info("Saving booking: {}", bookingSaveRequestDTO);
        Boat boat = boatRepository.findById(bookingSaveRequestDTO.getBoatId())
                .orElseThrow(() -> new ResourceNotFoundException("Boat not found with ID: " + bookingSaveRequestDTO.getBoatId()));

        if (!isTimeSlotAvailable(bookingSaveRequestDTO.getBookingDate(), bookingSaveRequestDTO.getTimeSlot())) {
            throw new IllegalArgumentException("Time slot " + bookingSaveRequestDTO.getTimeSlot() + " is already booked for " + bookingSaveRequestDTO.getBookingDate());
        }

        // Valid promo codes
        List<String> validPromoCodes = Arrays.asList("DISCOUNT10", "SAVE20", "FREETRIP");
        String promoCode = bookingSaveRequestDTO.getPromoCode();
        if (promoCode != null && !promoCode.isEmpty() && !validPromoCodes.contains(promoCode)) {
            throw new IllegalArgumentException("Invalid promotional code: " + promoCode);
        }

        Booking booking = new Booking();
        booking.setBoat(boat);
        booking.setUserName(bookingSaveRequestDTO.getUserName());
        booking.setUserEmail(bookingSaveRequestDTO.getUserEmail());
        booking.setUserPhone(bookingSaveRequestDTO.getUserPhone());
        booking.setBookingDate(bookingSaveRequestDTO.getBookingDate());
        booking.setTimeSlot(bookingSaveRequestDTO.getTimeSlot());
        booking.setPromoCode(promoCode);
        String bookingCode = codeGenerator.generateUniqueBookingCode();
        booking.setBookingCode(bookingCode);
        booking.setStatus("confirmed");

        bookingRepository.save(booking);

        // Email sending logic
        try {
            logger.info("Sending confirmation email to: {}", booking.getUserEmail());
            emailService.sendBookingConfirmationEmail(
                    booking.getUserEmail(),
                    booking.getBookingCode(),
                    booking.getUserName(),
                    boat.getName(),
                    boat.getLocation(),
                    booking.getBookingDate().toString(),
                    booking.getTimeSlot().getDisplayName()
            );
            logger.info("Email sent successfully to: {}", booking.getUserEmail());
        } catch (MailAuthenticationException e) {
            logger.error("Email authentication failed: {}", e.getMessage());
            // Optionally propagate to frontend
            throw new RuntimeException("Failed to send confirmation email due to authentication issue", e);
        } catch (MessagingException e) {
            logger.error("Failed to send email due to messaging error: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send confirmation email", e);
        } catch (Exception e) {
            logger.error("Unexpected error sending email: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error sending confirmation email", e);
        }

        return booking;
    }

    @Override
    public GetBookingByCodeResponseDTO getBookingByCode(String bookingCode) {
        Booking booking = bookingRepository.findByBookingCode(bookingCode)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with code: " + bookingCode));

        String boatName = booking.getBoat() != null ? booking.getBoat().getName() : "Unknown Boat";

        return new GetBookingByCodeResponseDTO(
                boatName,
                booking.getUserName(),
                booking.getUserEmail(),
                booking.getUserPhone(),
                booking.getBookingDate(),
                booking.getTimeSlot()
        );
    }

    @Override
    public List<AvailableSlotsResponseDTO> getAvailableSlots() {
        List<AvailableSlotsResponseDTO> availableSlots = new ArrayList<>();
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        List<TimeSlot> allTimeSlots = Arrays.asList(TimeSlot.values());

        for (int i = 0; i < TWO_MONTHS_IN_DAYS; i++) {
            LocalDate date = tomorrow.plusDays(i);
            List<Booking> bookingsForDate = bookingRepository.findByBookingDate(date);

            List<TimeSlot> bookedSlots = bookingsForDate.stream()
                    .map(Booking::getTimeSlot)
                    .collect(Collectors.toList());

            List<String> availableTimeSlots = allTimeSlots.stream()
                    .filter(slot -> !bookedSlots.contains(slot))
                    .map(TimeSlot::getDisplayName)
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

    @Override
    public GetBookingByEmailResponseDTO getBookingByemail(String email) {
        Booking booking = bookingRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with email: " + email));

        String boatName = booking.getBoat() != null ? booking.getBoat().getName() : "Unknown Boat";

        return new GetBookingByEmailResponseDTO(
                boatName,
                booking.getUserName(),
                booking.getUserEmail(),
                booking.getUserPhone(),
                booking.getBookingDate(),
                booking.getTimeSlot()
        );
    }
}