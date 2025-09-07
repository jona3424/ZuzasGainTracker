package com.zuzasgaintracker.demo.mapper;

import com.zuzasgaintracker.demo.dto.TrainingPlanRequest;
import com.zuzasgaintracker.demo.dto.TrainingPlanResponse;
import com.zuzasgaintracker.demo.entity.Exercise;
import com.zuzasgaintracker.demo.entity.TrainingPlan;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { ExerciseMapper.class })
public abstract class TrainingPlanMapper {

    public abstract TrainingPlanResponse toResponse(TrainingPlan entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "workoutSessions", ignore = true)
    public abstract TrainingPlan fromRequest(TrainingPlanRequest req);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "workoutSessions", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    public abstract void updateEntityFromRequest(TrainingPlanRequest req, @MappingTarget TrainingPlan entity);

    @AfterMapping
    protected void linkChildren(@MappingTarget TrainingPlan plan) {
        if (plan.getExercises() != null) {
            for (Exercise e : plan.getExercises()) {
                e.setTrainingPlan(plan);
            }
        }
    }
}
