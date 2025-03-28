package com.wondersri.wondersri.repo;

import com.wondersri.wondersri.Enum.TimeSlot;
import com.wondersri.wondersri.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingCode(String bookingCode);
    List<Booking> findByBookingDate(LocalDate date);
    List<Booking> findByBookingDateAndTimeSlot(LocalDate date, TimeSlot timeSlot);
    Optional<Booking> findByUserEmail(String email); // Add this
}