package com.wondersri.wondersri.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoatSaveRequestDTO {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @Positive(message = "Capacity must be a positive number")
    private int capacity;
    @Positive(message = "price must be a positive number")
    private int Price;
    private String description;
    @NotBlank(message = "Location is required")
    private String location;

}
