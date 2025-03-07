package com.wondersri.wondersri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "boats")
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // e.g., "Sunset Cruise", "Fishing Trip"

    @Column(nullable = false)
    private int capacity; // Maximum number of passengers

    @Column
    private String description; // Optional description of the service

    @Column(nullable = false)
    private String location; // Location of the boat

    public Boat(Long id, String name, int capacity, String description, String location) {
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

    public Boat() {
    }
}
