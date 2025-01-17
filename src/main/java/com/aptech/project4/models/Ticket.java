package com.aptech.project4.models;

import java.util.*;

import jakarta.persistence.*;
@Entity
@Table(name = "phieu_kham")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String description;

    // Getters and Setters
}
