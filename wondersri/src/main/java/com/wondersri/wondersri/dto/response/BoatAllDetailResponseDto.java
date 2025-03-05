package com.wondersri.wondersri.dto.response;

public class BoatAllDetailResponseDto {
    private Long id;
    private String name;
    private int capacity;
    private String description;
    private String location;

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

    public BoatAllDetailResponseDto() {
    }

    public BoatAllDetailResponseDto(Long id, String name, int capacity, String description, String location) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.location = location;
    }
}
