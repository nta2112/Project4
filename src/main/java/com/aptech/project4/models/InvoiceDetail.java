package com.aptech.project4.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

// import java.util.*;

import jakarta.persistence.*;

@Entity
// Bảng chi tiết hóa đơn
@Table(name = "invoice_details")
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Service service;

    private Integer quantity;
    private String description;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
