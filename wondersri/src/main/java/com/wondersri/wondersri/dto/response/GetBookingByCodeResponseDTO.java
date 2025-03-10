package com.wondersri.wondersri.dto.response;

import com.wondersri.wondersri.Enum.TimeSlot;

import java.time.LocalDate;

public class GetBookingByCodeResponseDTO {
    private Long id;
    private String boatName;
    private String userName;
    private String userEmail;
    private String userPhone;
    private LocalDate bookingDate;
    private TimeSlot timeSlot;
    private String bookingCode;
    private String status;

    public GetBookingByCodeResponseDTO(Long id, String boatName, String userName, String userEmail, String userPhone, LocalDate bookingDate, TimeSlot timeSlot, String bookingCode, String status) {
        this.id = id;
        this.boatName = boatName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.bookingDate = bookingDate;
        this.timeSlot = timeSlot;
        this.bookingCode = bookingCode;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoatName() {
        return boatName;
    }

    public void setBoatName(String boatName) {
        this.boatName = boatName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
