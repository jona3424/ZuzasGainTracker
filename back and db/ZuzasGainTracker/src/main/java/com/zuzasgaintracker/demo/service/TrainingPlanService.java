package com.zuzasgaintracker.demo.service;

import com.zuzasgaintracker.demo.dto.ExerciseDto;
import com.zuzasgaintracker.demo.dto.TrainingPlanRequest;
import com.zuzasgaintracker.demo.dto.TrainingPlanResponse;
import com.zuzasgaintracker.demo.entity.Exercise;
import com.zuzasgaintracker.demo.entity.TrainingPlan;
import com.zuzasgaintracker.demo.entity.User;
import com.zuzasgaintracker.demo.mapper.ExerciseMapper;
import com.zuzasgaintracker.demo.mapper.TrainingPlanMapper;
import com.zuzasgaintracker.demo.repository.TrainingPlanRepository;
import com.zuzasgaintracker.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingPlanService {

    private final TrainingPlanRepository planRepo;
    private final UserRepository userRepo;
    private final TrainingPlanMapper planMapper;
    private final ExerciseMapper exerciseMapper;

    private User requireUser(Principal principal) {
        return userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("Authenticated user not found"));
    }

    @Transactional
    public TrainingPlanResponse create(Principal principal, TrainingPlanRequest req) {
        User user = requireUser(principal);

        TrainingPlan plan = planMapper.fromRequest(req);
        plan.setUser(user);

        TrainingPlan saved = planRepo.save(plan);
        return planMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<TrainingPlanResponse> findAllForUser(Principal principal) {
        User user = requireUser(principal);
        return planRepo.findAllByUser_IdOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(planMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TrainingPlanResponse getOne(Principal principal, UUID planId) {
        User user = requireUser(principal);
        TrainingPlan plan = planRepo.findByIdAndUser_Id(planId, user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));
        return planMapper.toResponse(plan);
    }

    @Transactional
    public TrainingPlanResponse update(Principal principal, UUID planId, TrainingPlanRequest req) {
        User user = requireUser(principal);
        TrainingPlan plan = planRepo.findByIdAndUser_Id(planId, user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));

        planMapper.updateEntityFromRequest(req, plan);

        syncExercises(plan, req.getExercises());

        TrainingPlan saved = planRepo.save(plan);
        return planMapper.toResponse(saved);
    }

    @Transactional
    public void delete(Principal principal, UUID planId) {
        User user = requireUser(principal);
        TrainingPlan plan = planRepo.findByIdAndUser_Id(planId, user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));
        planRepo.delete(plan);
    }

    @Transactional
    public TrainingPlanResponse setActive(Principal principal, UUID planId) {
        User user = requireUser(principal);
        TrainingPlan plan = planRepo.findByIdAndUser_Id(planId, user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));

        planRepo.deactivateAllForUser(user.getId());
        plan.setIsActive(true);
        user.setLastActivePlan(plan);

        TrainingPlan saved = planRepo.save(plan);
        return planMapper.toResponse(saved);
    }

    private void syncExercises(TrainingPlan plan, List<ExerciseDto> incoming) {
        incoming = (incoming == null) ? List.of() : incoming;

        Map<UUID, Exercise> existingById = plan.getExercises().stream()
                .filter(e -> e.getId() != null)
                .collect(Collectors.toMap(Exercise::getId, Function.identity()));

        List<Exercise> newState = new ArrayList<>(incoming.size());

        for (ExerciseDto dto : incoming) {
            if (dto.getId() != null && existingById.containsKey(dto.getId())) {
                Exercise entity = existingById.get(dto.getId());
                exerciseMapper.updateEntityFromDto(dto, entity);
                newState.add(entity);
            } else {
                Exercise entity = exerciseMapper.fromDto(dto);
                entity.setTrainingPlan(plan);
                newState.add(entity);
            }
        }

        plan.getExercises().clear();
        plan.getExercises().addAll(newState);
    }
}
