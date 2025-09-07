package com.zuzasgaintracker.demo.dto;

import com.zuzasgaintracker.demo.entity.Unit;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ExerciseDto {
    private UUID id;
    @NotBlank private String name;
    private String muscleGroup;
    private String notes;
    @NotNull private Integer targetSets;
    @NotNull private Integer targetRepsMin;
    @NotNull private Integer targetRepsMax;
    @NotNull private Integer recommendedRestSeconds;
    @NotNull private Integer sequenceOrder;
    private String dayOfWeek;
    private Integer workoutDaySequence;
    private Double recommendedWeightStart;
    @NotNull private Unit unit;
}
