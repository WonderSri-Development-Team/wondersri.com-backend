package com.wondersri.wondersri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(nullable = false)
    private int price;
    @Column
    private String description; // Optional description of the service
    @Column(nullable = false)
    private String location; // Location of the boat

}
