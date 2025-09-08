package com.zuzasgaintracker.demo.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDayRequestDto {
    private UUID id;
    private String label;
    private Byte dayOfWeek;
    private Integer sequenceOrder;
    private List<PlanExerciseRequestDto> exercises;
}
