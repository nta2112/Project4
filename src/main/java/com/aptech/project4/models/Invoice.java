package com.aptech.project4.models;

import java.util.*;

import jakarta.persistence.*;
@Entity
@Table(name = "hoa_don")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    private String status;
    private Double totalAmount;

    // Getters and Setters
}
