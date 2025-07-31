package com.travelify.travelify.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "image_path")
    private String imagePath;

    private String area;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "bed_type")
    private String bedType;

    private String view;

    @ElementCollection
    @CollectionTable(name = "room_facilities", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "facility")
    @OrderColumn(name = "facility_order")
    private List<String> facilities;

    private String price;

    @Column(name = "old_price")
    private String oldPrice;

    @Column(name = "breakfast_included")
    private boolean breakfastIncluded;

    @Column(name = "free_cancel")
    private boolean freeCancel;

    @Column(name = "max_people")
    private int maxPeople;

    @Column(name = "hotel_id")
    private Long hotelId;
}
