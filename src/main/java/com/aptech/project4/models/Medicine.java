package com.aptech.project4.models;

import java.util.*;

import jakarta.persistence.*;
@Entity
@Table(name = "thuoc")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    // Getters and Setters
}
