package com.wondersri.wondersri.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoatResponseDTO {
    private Long id;
    private String name;
    private int capacity;
    private String Price;
    private String description;
    private String location;
    private List<String> imageUrls;

}