package com.wondersri.wondersri.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class BoatSaveRequestDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Positive(message = "Capacity must be a positive number")
    private int capacity;

    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    public BoatSaveRequestDTO() {
    }

    public BoatSaveRequestDTO(Long id, String name, int capacity, String description, String location) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.location = location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }
}
