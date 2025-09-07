package com.zuzasgaintracker.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private java.util.UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private TrainingPlan trainingPlan;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "muscle_group", length = 100)
    private String muscleGroup;

    @Column(name = "notes", columnDefinition = "TEXT")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false, length = 10)
    private Unit unit = Unit.KG;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseLog> exerciseLogs = new ArrayList<>();
}
