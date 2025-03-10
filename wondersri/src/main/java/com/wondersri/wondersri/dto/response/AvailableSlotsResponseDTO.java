package com.wondersri.wondersri.dto.response;

import com.wondersri.wondersri.Enum.TimeSlot;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AvailableSlotsResponseDTO {
    private LocalDate date;

    public AvailableSlotsResponseDTO() {
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<String> availableSlots) {
        this.availableSlots = availableSlots;
    }

    private List<String> availableSlots;  // Changed to String to use display names

    public AvailableSlotsResponseDTO(LocalDate date, List<TimeSlot> availableSlots) {
        this.date = date;
        this.availableSlots = availableSlots.stream()
                .map(TimeSlot::getDisplayName)
                .collect(Collectors.toList());
    }
}