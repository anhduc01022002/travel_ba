package com.travelify.travelify.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "image_url")
    @JsonProperty("image")
    private String imageUrl;

    private Double rate;

    private Double price;

    @Column(nullable = false)
    @JsonProperty("address")
    private String address;

    @ElementCollection
    @CollectionTable(name = "hotel_amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity")
    private List<String> amenities;

    private Boolean isAvailable;

    @Column(columnDefinition = "TEXT")
    private String description;
}
