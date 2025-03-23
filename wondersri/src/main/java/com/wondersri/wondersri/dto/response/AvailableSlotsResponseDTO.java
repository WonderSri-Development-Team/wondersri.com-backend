package com.wondersri.wondersri.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSlotsResponseDTO {
    private LocalDate date;
    private List<String> availableSlots; // Changed to String for display names
}