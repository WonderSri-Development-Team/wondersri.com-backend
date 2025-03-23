package com.wondersri.wondersri.dto.request;

import com.wondersri.wondersri.Enum.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    private String promoCode; // Optional promotional code
}