package com.zuzasgaintracker.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "plan_days")
public class PlanDay {

    @Id
    @GeneratedValue @UuidGenerator
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", columnDefinition = "VARCHAR(36)", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private TrainingPlan plan;

    @Column(name = "label", length = 50, nullable = false)
    private String label;

    @Column(name = "day_of_week")
    private Byte dayOfWeek;

    @Column(name = "sequence_order", nullable = false)
    private Integer sequenceOrder;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequenceOrder ASC")
    @Fetch(FetchMode.SUBSELECT)
    private List<PlanExercise> exercises = new ArrayList<>();


    public void addExercise(PlanExercise e){
        exercises.add(e);
        e.setDay(this);
        e.setPlan(this.plan);
    }
    public void removeExercise(PlanExercise e){
        exercises.remove(e);
        e.setDay(null);
    }
}
