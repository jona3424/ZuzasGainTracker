package com.zuzasgaintracker.demo.service;

import com.zuzasgaintracker.demo.dto.*;
import com.zuzasgaintracker.demo.entity.*;
import com.zuzasgaintracker.demo.mapper.ExerciseCatalogResolver;
import com.zuzasgaintracker.demo.mapper.PlanExerciseMapper;
import com.zuzasgaintracker.demo.mapper.TrainingPlanMapper;
import com.zuzasgaintracker.demo.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
@Transactional
@RequiredArgsConstructor
public class TrainingPlanService {

    private final TrainingPlanRepository plans;
    private final ExerciseCatalogRepository ecRepo;
    private final PlanDayRepository dayRepo;
    private final PlanExerciseRepository peRepo;
    private final TrainingPlanMapper planMapper;
    private final PlanExerciseMapper peMapper;
    private final ExerciseCatalogResolver ecResolver;
    private final UserRepository userRepository;

    public TrainingPlanResponse create(UUID userId, TrainingPlanRequest req) {
        User user = userRepository.getReferenceById(userId);

        TrainingPlan plan = planMapper.toEntity(req);
        plan.setUser(user);

        plan.getDays().clear();
        if (req.getDays() != null) {
            for (var d : req.getDays()) {
                PlanDay day = new PlanDay();
                day.setPlan(plan);
                day.setLabel(d.getLabel());
                day.setDayOfWeek(d.getDayOfWeek());
                day.setSequenceOrder(d.getSequenceOrder());

                if (d.getExercises() != null) {
                    for (var exDto : d.getExercises()) {
                        PlanExercise pe = peMapper.toEntity(exDto, ecResolver);
                        pe.setPlan(plan);
                        pe.setDay(day);
                        day.getExercises().add(pe);
                    }
                }
                plan.getDays().add(day);
            }
        }

        plan = plans.save(plan);

        if (Boolean.TRUE.equals(req.getActive())) {
            setActive(userId, plan.getId());
        }
        return planMapper.toResponse(loadDeepOrThrow(plan.getId(), userId));
    }

    @Transactional(readOnly = true)
    public List<TrainingPlanResponse> listMine(UUID userId) {
        return plans.findAllDeepByUserId(userId).stream()
                .sorted(Comparator.comparing(TrainingPlan::getCreatedAt).reversed())
                .map(planMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TrainingPlanResponse get(UUID userId, UUID planId) {
        return planMapper.toResponse(loadDeepOrThrow(planId, userId));
    }

    @Transactional
    public TrainingPlanResponse update(UUID userId, UUID planId, TrainingPlanRequest req) {
        TrainingPlan plan = loadDeepOrThrow(planId, userId);

        plan.setName(req.getName());
        plan.setDescription(req.getDescription());

        Set<UUID> incomingDayIds = (req.getDays() == null) ? Set.of()
                : req.getDays().stream()
                .map(PlanDayRequestDto::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<PlanDay> toDeleteDays = plan.getDays().stream()
                .filter(d -> d.getId() != null && !incomingDayIds.contains(d.getId()))
                .toList();

        toDeleteDays.forEach(plan::removeDay);
        plans.flush();

        Map<UUID, PlanDay> byId = plan.getDays().stream()
                .filter(d -> d.getId() != null)
                .collect(Collectors.toMap(PlanDay::getId, Function.identity()));

        if (req.getDays() != null) {
            for (var dReq : req.getDays()) {
                PlanDay day = (dReq.getId() != null) ? byId.get(dReq.getId()) : null;
                if (day == null) {
                    day = new PlanDay();
                    day.setPlan(plan);
                    plan.getDays().add(day);
                }
                day.setLabel(dReq.getLabel());
                day.setDayOfWeek(dReq.getDayOfWeek());
                day.setSequenceOrder(dReq.getSequenceOrder());

                Set<UUID> incomingExIds = (dReq.getExercises() == null) ? Set.of()
                        : dReq.getExercises().stream()
                        .map(PlanExerciseRequestDto::getId)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());

                List<PlanExercise> exToDelete = new ArrayList<>();
                for (PlanExercise ex : new ArrayList<>(day.getExercises())) {
                    if (ex.getId() != null && !incomingExIds.contains(ex.getId())) {
                        exToDelete.add(ex);
                    }
                }
                exToDelete.forEach(day::removeExercise);
                // optional: plans.flush(); // only needed if you also have a unique on (day_id, sequence_order)

                Map<UUID, PlanExercise> exById = day.getExercises().stream()
                        .filter(e -> e.getId() != null)
                        .collect(Collectors.toMap(PlanExercise::getId, Function.identity()));

                if (dReq.getExercises() != null) {
                    for (var exReq : dReq.getExercises()) {
                        PlanExercise pe = (exReq.getId() != null) ? exById.get(exReq.getId()) : null;
                        if (pe == null) {
                            pe = new PlanExercise();
                            pe.setPlan(plan);
                            pe.setDay(day);
                            day.getExercises().add(pe);
                        }
                        pe.setExercise(ecResolver.resolve(exReq.getExerciseCatalogId()));
                        pe.setSequenceOrder(exReq.getSequenceOrder());
                        pe.setTargetSets(exReq.getTargetSets());
                        pe.setTargetRepsMin(exReq.getTargetRepsMin());
                        pe.setTargetRepsMax(exReq.getTargetRepsMax());
                        pe.setRecommendedRestSeconds(exReq.getRecommendedRestSeconds());
                        pe.setRecommendedWeightStartKg(exReq.getRecommendedWeightStartKg());
                        pe.setRpeTarget(exReq.getRpeTarget());
                        pe.setTempo(exReq.getTempo());
                        pe.setNotes(exReq.getNotes());
                    }
                }

                renumberExercises(day);
            }
        }

        renumberDays(plan);

        plans.saveAndFlush(plan);
        return planMapper.toResponse(loadDeepOrThrow(plan.getId(), userId));
    }

    private void renumberDays(TrainingPlan plan) {
        plan.getDays().sort(Comparator
                .comparing(PlanDay::getSequenceOrder, Comparator.nullsLast(Integer::compareTo))
                .thenComparing(d -> d.getId() == null ? UUID.randomUUID() : d.getId()));
        int i = 1;
        for (PlanDay d : plan.getDays()) d.setSequenceOrder(i++);
    }

    private void renumberExercises(PlanDay day) {
        day.getExercises().sort(Comparator
                .comparing(PlanExercise::getSequenceOrder, Comparator.nullsLast(Integer::compareTo))
                .thenComparing(e -> e.getId() == null ? UUID.randomUUID() : e.getId()));
        int i = 1;
        for (PlanExercise e : day.getExercises()) e.setSequenceOrder(i++);
    }


    public void delete(UUID userId, UUID planId) {
        TrainingPlan plan = plans.findDeepByIdAndUserId(planId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));
        plans.delete(plan);
    }

    public TrainingPlanResponse setActive(UUID userId, UUID planId) {
        List<TrainingPlan> mine = plans.findAllByUser_IdOrderByCreatedAtDesc(userId);
        for (var p : mine) p.setActive(p.getId().equals(planId));

        User user = userRepository.getReferenceById(userId);
        TrainingPlan active = mine.stream()
                .filter(p -> p.getId().equals(planId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));
        user.setLastActivePlan(active);

        return planMapper.toResponse(loadDeepOrThrow(planId, userId));
    }

    private TrainingPlan loadDeepOrThrow(UUID planId, UUID userId) {
        return plans.findDeepByIdAndUserId(planId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));
    }
}
