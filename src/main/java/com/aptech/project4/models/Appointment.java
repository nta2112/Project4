package com.aptech.project4.models;

import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "lich_hen")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receptionist_id")
    private User receptionist;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;

    // Getters and Setters
}

