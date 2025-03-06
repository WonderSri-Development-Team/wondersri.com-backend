package com.wondersri.wondersri.Util;

import com.wondersri.wondersri.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CodeGenerator {

    @Autowired
    private BookingRepository bookingRepository;

    public String generateUniqueBookingCode() {
        String bookingCode;
        do {
            // Generate a random code
            bookingCode = "BOOK-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        } while (isCodeAlreadyExists(bookingCode)); // Ensure the code is unique
        return bookingCode;
    }

    private boolean isCodeAlreadyExists(String bookingCode) {
        return bookingRepository.findByBookingCode(bookingCode).isPresent();
    }
}