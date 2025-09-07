package com.zuzasgaintracker.demo.mapper;

import com.zuzasgaintracker.demo.dto.ExerciseDto;
import com.zuzasgaintracker.demo.entity.Exercise;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    ExerciseDto toDto(Exercise entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trainingPlan", ignore = true)
    @Mapping(target = "exerciseLogs", ignore = true)
    Exercise fromDto(ExerciseDto dto);

    void updateEntityFromDto(ExerciseDto dto, @MappingTarget Exercise entity);
}
