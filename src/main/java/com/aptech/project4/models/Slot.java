package com.aptech.project4.models;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "slots")
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timeSlot;

    // Getters and Setters
}
