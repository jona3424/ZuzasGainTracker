package com.zuzasgaintracker.demo.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private TrainingPlan plan;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "muscle_group", length = 100)
    private String muscleGroup;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "target_sets", nullable = false)
    private Integer targetSets;

    @Column(name = "target_reps_min", nullable = false)
    private Integer targetRepsMin;

    @Column(name = "target_reps_max", nullable = false)
    private Integer targetRepsMax;

    @Column(name = "recommended_rest_seconds", nullable = false)
    private Integer recommendedRestSeconds;

    @Column(name = "sequence_order", nullable = false)
    private Integer sequenceOrder;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Column(name = "workout_day_sequence")
    private Integer workoutDaySequence;

    @Column(name = "recommended_weight_start")
    private Double recommendedWeightStart;

    @Column(name = "unit", nullable = false, length = 10)
    private String unit;

}