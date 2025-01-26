package com.aptech.project4.models;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String dob;
    private String gender;

    // Getters and Setters
}
