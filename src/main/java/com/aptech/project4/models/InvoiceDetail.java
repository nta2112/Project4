package com.aptech.project4.models;

import java.util.*;

import jakarta.persistence.*;
@Entity
@Table(name = "chi_tiet_hoa_don_thuoc")
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = true)
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = true)
    private Service service;

    private Integer quantity;
    private String description;

    // Getters and Setters
}
