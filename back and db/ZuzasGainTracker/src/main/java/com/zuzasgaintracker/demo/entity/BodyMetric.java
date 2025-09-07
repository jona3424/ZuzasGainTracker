package com.zuzasgaintracker.demo.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "body_metrics")
public class BodyMetric {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "logged_at", nullable = false)
    private Instant loggedAt;

    @Column(name = "weight_kg")
    private Double weightKg;

    @Column(name = "body_fat_percentage")
    private Double bodyFatPercentage;

    @Column(name = "bmi")
    private Double bmi;

    @Column(name = "bicep_cm")
    private Double bicepCm;

    @Column(name = "waist_cm")
    private Double waistCm;

    @Lob
    @Column(name = "notes")
    private String notes;

}