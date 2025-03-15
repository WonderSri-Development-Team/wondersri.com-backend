package com.wondersri.wondersri.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoatAllDetailResponseDto {
    private Long id;
    private String name;
    private int capacity;
    private int price;
    private String description;
    private String location;

}
