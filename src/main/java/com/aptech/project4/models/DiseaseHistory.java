package com.aptech.project4.models;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "disease_histories")
public class DiseaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String description;

    // Getters and Setters
}
