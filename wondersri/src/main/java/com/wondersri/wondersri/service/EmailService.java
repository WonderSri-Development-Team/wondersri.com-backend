package com.wondersri.wondersri.service;

import javax.mail.MessagingException;

public interface EmailService {
    void sendBookingConfirmationEmail(String toEmail, String bookingCode, String userName,
                                      String boatName, String boatLocation, String bookingDate,
                                      String timeSlot) throws MessagingException;
}