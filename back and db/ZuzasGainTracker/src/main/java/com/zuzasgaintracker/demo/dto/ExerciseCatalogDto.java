package com.zuzasgaintracker.demo.dto;

import lombok.*;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ExerciseCatalogDto {
    private UUID id;
    private String name;
    private String muscleGroup;
    private String equipment;
    private String pattern;
    private Boolean isUnilateral;
    private String notes;
}

