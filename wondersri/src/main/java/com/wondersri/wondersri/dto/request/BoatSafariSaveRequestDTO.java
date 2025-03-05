package com.wondersri.wondersri.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoatSafariSaveRequestDTO {

    private Long id;
    private String name; // e.g., "Sunset Cruise", "Fishing Trip"
    private int capacity; // Maximum number of passengers
    private String description; // Optional description of the service
    private String location;


}
