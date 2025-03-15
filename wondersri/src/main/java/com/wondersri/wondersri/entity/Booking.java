package com.wondersri.wondersri.entity;

import com.wondersri.wondersri.Enum.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}