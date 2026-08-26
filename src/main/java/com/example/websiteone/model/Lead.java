package com.example.websiteone.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "leads")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false)
    private String clientname;

    @Column(nullable = false)
    private String email;

    @Column(name = "service_type", nullable = false)
    private String serviceType;

    @Column(name = "estimated_budget")
    private Double estimatedBudget;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}
