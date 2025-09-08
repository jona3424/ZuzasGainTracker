package com.zuzasgaintracker.demo.mapper;

import com.zuzasgaintracker.demo.dto.TrainingPlanRequest;
import com.zuzasgaintracker.demo.dto.TrainingPlanResponse;
import com.zuzasgaintracker.demo.entity.TrainingPlan;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {PlanDayMapper.class})
public interface TrainingPlanMapper {


    TrainingPlanResponse toResponse(TrainingPlan plan);

    @InheritInverseConfiguration(name = "toResponse")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "days", ignore = true)
    @Mapping(target = "active", source = "active", defaultValue = "false")
    TrainingPlan toEntity(TrainingPlanRequest request);

    @AfterMapping
    default void linkDays(@MappingTarget TrainingPlan plan,
                          TrainingPlanRequest req,
                          @Context PlanDayMapper dayMapper,
                          @Context ExerciseCatalogResolver resolver) {
        plan.getDays().clear();
        if (req.getDays() != null) {
            req.getDays().forEach(d -> {
                var day = dayMapper.toEntity(d);
                dayMapper.linkChildren(day, d, plan, resolver, null);
                plan.getDays().add(day);
            });
        }
    }
}
