package com.wondersri.wondersri.dto.response;

import java.util.List;

public class BoatAllDetailResponseDto {
    private Long id;
    private String name;
    private int capacity;
    private String description;
    private String location;
    private List<String> imageUrls;

    public BoatAllDetailResponseDto(Long id, String name, int capacity, String description, String location, List<String> imageUrls) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.location = location;
        this.imageUrls = imageUrls;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public List<String> getImageUrls() { return imageUrls; }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }
}