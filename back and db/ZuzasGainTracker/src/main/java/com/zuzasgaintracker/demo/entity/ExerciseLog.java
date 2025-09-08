package com.zuzasgaintracker.demo.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "exercise_logs")
public class ExerciseLog {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private WorkoutSession session;


    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;

    @Column(name = "set_number", nullable = false)
    private Integer setNumber;

    @Column(name = "reps", nullable = false)
    private Integer reps;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "unit", nullable = false, length = 10)
    private String unit;

    @Column(name = "rpe")
    private Integer rpe;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "logged_at", nullable = false)
    private Instant loggedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "plan_exercise_id")
    private PlanExercise planExercise;

    @NotNull
    @Column(name = "weight_kg", nullable = false)
    private Double weightKg;

    @NotNull
    @ColumnDefault("'KG'")
    @Lob
    @Column(name = "unit_entered", nullable = false)
    private String unitEntered;

    @Column(name = "weight_entered")
    private Double weightEntered;

}