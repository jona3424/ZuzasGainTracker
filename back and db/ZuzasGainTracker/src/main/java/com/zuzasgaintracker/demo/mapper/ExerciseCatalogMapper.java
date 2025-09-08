package com.zuzasgaintracker.demo.mapper;

import com.zuzasgaintracker.demo.dto.ExerciseCatalogDto;
import com.zuzasgaintracker.demo.entity.ExerciseCatalog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseCatalogMapper {
    ExerciseCatalogDto toDto(ExerciseCatalog entity);
    ExerciseCatalog toEntity(ExerciseCatalogDto dto);
}
