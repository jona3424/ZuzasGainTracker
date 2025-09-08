package com.zuzasgaintracker.demo.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDayResponseDto {
    private UUID id;
    private String label;
    private Byte dayOfWeek;
    private Integer sequenceOrder;
    private List<PlanExerciseResponseDto> exercises;
}
