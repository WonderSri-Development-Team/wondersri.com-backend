package com.wondersri.wondersri.service;

import com.wondersri.wondersri.dto.request.BookingSaveRequestDTO;
import com.wondersri.wondersri.dto.response.AvailableSlotsResponseDTO;
import com.wondersri.wondersri.dto.response.BookingAllDetailDTO;
import com.wondersri.wondersri.dto.response.GetBookingByCodeResponseDTO;
import com.wondersri.wondersri.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking saveBooking(BookingSaveRequestDTO bookingSaveRequestDTO);

    GetBookingByCodeResponseDTO getBookingByCode(String bookingCode);

    List<AvailableSlotsResponseDTO> getAvailableSlots();

    List<BookingAllDetailDTO> getAllBookings();
}
