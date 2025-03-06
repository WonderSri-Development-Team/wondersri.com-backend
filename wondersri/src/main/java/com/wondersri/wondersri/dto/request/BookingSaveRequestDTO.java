package com.wondersri.wondersri.dto.request;

import com.wondersri.wondersri.Enum.TimeSlot;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class BookingSaveRequestDTO {
    @NotNull(message = "Boat ID is required")
    private Long boatId;

    @NotBlank(message = "User name is required")
    private String userName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "User email is required")
    private String userEmail;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    @NotBlank(message = "User phone is required")
    private String userPhone;

    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;

    @NotNull(message = "Time slot is required")
    private TimeSlot timeSlot;

    public BookingSaveRequestDTO(Long boatId, String userName, String userEmail, String userPhone, LocalDate bookingDate, TimeSlot timeSlot) {
        this.boatId = boatId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.bookingDate = bookingDate;
        this.timeSlot = timeSlot;
    }

    public BookingSaveRequestDTO() {
    }

    public Long getBoatId() {
        return boatId;
    }

    public void setBoatId(Long boatId) {
        this.boatId = boatId;
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
}
