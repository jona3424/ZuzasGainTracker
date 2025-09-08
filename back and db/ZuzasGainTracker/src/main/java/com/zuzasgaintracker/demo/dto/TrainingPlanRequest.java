package com.zuzasgaintracker.demo.dto;

import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TrainingPlanRequest {
    private String name;
    private String description;
    private Boolean active;
    private List<PlanDayRequestDto> days;
}
