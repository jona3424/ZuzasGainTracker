package com.zuzasgaintracker.demo.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "plan_exercises")
public class PlanExercise {

    @Id
    @GeneratedValue @UuidGenerator
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", columnDefinition = "VARCHAR(36)", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private TrainingPlan plan;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseCatalog exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id")
    private PlanDay day;

    @Column(name = "sequence_order", nullable = false)
    private Integer sequenceOrder;

    @Column(name = "target_sets", nullable = false)
    private Integer targetSets;

    @Column(name = "target_reps_min", nullable = false)
    private Integer targetRepsMin;

    @Column(name = "target_reps_max", nullable = false)
    private Integer targetRepsMax;

    @Column(name = "recommended_rest_seconds", nullable = false)
    private Integer recommendedRestSeconds;

    @Column(name = "recommended_weight_start_kg")
    private Double recommendedWeightStartKg;

    @Column(name = "rpe_target")
    private Byte rpeTarget;

    @Column(name = "tempo", length = 20)
    private String tempo;

    @Lob
    @Column(name = "notes")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
