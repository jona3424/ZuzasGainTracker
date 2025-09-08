package com.zuzasgaintracker.demo.mapper;

import com.zuzasgaintracker.demo.dto.PlanDayRequestDto;
import com.zuzasgaintracker.demo.dto.PlanDayResponseDto;
import com.zuzasgaintracker.demo.entity.PlanDay;
import com.zuzasgaintracker.demo.entity.PlanExercise;
import com.zuzasgaintracker.demo.entity.TrainingPlan;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PlanExerciseMapper.class})
public interface PlanDayMapper {

    @Mapping(target = "plan", ignore = true)
    @Mapping(target = "exercises", ignore = true)
    PlanDay toEntity(PlanDayRequestDto dto);

    PlanDayResponseDto toResponse(PlanDay entity);

    List<PlanDayResponseDto> toResponseList(List<PlanDay> entities);

    @AfterMapping
    default void linkChildren(@MappingTarget PlanDay day,
                              PlanDayRequestDto dto,
                              @Context TrainingPlan plan,
                              @Context ExerciseCatalogResolver resolver,
                              PlanExerciseMapper peMapper) {
        day.setPlan(plan);
        day.getExercises().clear();
        if (dto.getExercises() != null) {
            for (var exDto : dto.getExercises()) {
                PlanExercise pe = peMapper.toEntity(exDto, resolver);
                pe.setPlan(plan);
                pe.setDay(day);
                day.getExercises().add(pe);
            }
        }
    }
}
