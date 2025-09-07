package com.zuzasgaintracker.demo.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TrainingPlanResponse {
    private UUID id;
    private String name;
    private String description;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ExerciseDto> exercises;
}
