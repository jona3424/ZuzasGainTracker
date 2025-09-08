package com.zuzasgaintracker.demo.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanExerciseRequestDto {
    private UUID id;
    private UUID exerciseCatalogId;
    private Integer sequenceOrder;
    private Integer targetSets;
    private Integer targetRepsMin;
    private Integer targetRepsMax;
    private Integer recommendedRestSeconds;
    private Double recommendedWeightStartKg;
    private Byte rpeTarget;
    private String tempo;
    private String notes;
}
