package com.zuzasgaintracker.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "exercise_catalog")
public class ExerciseCatalog {

    @Id
    @GeneratedValue @UuidGenerator
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", columnDefinition = "VARCHAR(36)", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "muscle_group", length = 100)
    private String muscleGroup;

    @Column(name = "equipment", length = 100)
    private String equipment;

    @Column(name = "pattern", length = 50)
    private String pattern;

    @Column(name = "is_unilateral")
    private Boolean isUnilateral;

    @Lob
    @Column(name = "notes")
    private String notes;
}
