package com.aptech.project4.models;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "ca_truc")
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private Slot slot;

    // Getters and Setters
}
