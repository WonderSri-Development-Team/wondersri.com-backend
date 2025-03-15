package com.wondersri.wondersri.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoatAllDetailResponseDto {
    private Long id;
    private String name;
    private int capacity;
    private String Price;
    private String description;
    private String location;
    private List<String> imageUrls;

}