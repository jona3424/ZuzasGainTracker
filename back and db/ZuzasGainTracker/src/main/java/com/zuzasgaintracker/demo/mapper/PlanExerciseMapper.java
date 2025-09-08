package com.zuzasgaintracker.demo.mapper;

import com.zuzasgaintracker.demo.dto.PlanExerciseRequestDto;
import com.zuzasgaintracker.demo.dto.PlanExerciseResponseDto;
import com.zuzasgaintracker.demo.entity.PlanExercise;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ExerciseCatalogMapper.class})
public interface PlanExerciseMapper {

    @Mappings({
            @Mapping(target = "exercise", expression = "java(resolver.resolve(dto.getExerciseCatalogId()))"),
            @Mapping(target = "id", source = "dto.id"),
    })
    PlanExercise toEntity(PlanExerciseRequestDto dto, @Context ExerciseCatalogResolver resolver);

    @Mappings({
            @Mapping(target = "exercise", source = "exercise")
    })
    PlanExerciseResponseDto toResponse(PlanExercise entity);
}
