package com.wondersri.wondersri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "boats")
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @Column
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(name = "price", nullable = false, columnDefinition = "varchar(255) default '0'")
    private String Price;

    @OneToMany(mappedBy = "boat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    public void addImage(Image image) {
    }
}