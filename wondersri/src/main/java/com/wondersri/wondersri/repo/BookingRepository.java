package com.wondersri.wondersri.repo;

import com.wondersri.wondersri.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookingDate(LocalDate bookingDate); // Find all bookings for a specific date
    boolean existsByBookingDateAndTimeSlot(LocalDate bookingDate, String timeSlot); // Check if a slot is booked
}