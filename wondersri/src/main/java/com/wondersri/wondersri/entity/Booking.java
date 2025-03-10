package com.wondersri.wondersri.entity;

import com.wondersri.wondersri.Enum.TimeSlot;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boat_id", nullable = false)
    private Boat boat; // Reference to the Boat entity

    @Column(nullable = false)
    private String userName; // Name of the user

    @Column(nullable = false)
    private String userEmail; // Email of the user

    @Column(nullable = false)
    private String userPhone; // Phone number of the user

    @Column(nullable = false)
    private LocalDate bookingDate; // Date of the booking

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimeSlot timeSlot; // Selected time slot (e.g., SLOT_1, SLOT_2, SLOT_3)

    @Column(nullable = false, unique = true)
    private String bookingCode; // Unique booking code

    @Column(nullable = false)
    private String status; // Booking status (e.g., confirmed, pending, cancelled)

    public Booking(Long id, Boat boat, String userName, String userEmail, String userPhone, LocalDate bookingDate, TimeSlot timeSlot, String bookingCode, String status) {
        this.id = id;
        this.boat = boat;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.bookingDate = bookingDate;
        this.timeSlot = timeSlot;
        this.bookingCode = bookingCode;
        this.status = status;
    }

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
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