package com.wondersri.wondersri.dto.response;

import com.wondersri.wondersri.Enum.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBookingByCodeResponseDTO {
    private String boatName;
    private String userName;
    private String userEmail;
    private String userPhone;
    private LocalDate bookingDate;
    private TimeSlot timeSlot;
}
