package com.zuzasgaintracker.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TrainingPlanRequest {
    @NotBlank private String name;
    @Size(max = 65000)
    private String description;
    @Builder.Default
    private List<ExerciseDto> exercises = List.of();
}
