package com.zuzasgaintracker.demo.controller;

import com.zuzasgaintracker.demo.dto.*;
import com.zuzasgaintracker.demo.service.TrainingPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class TrainingPlanController {

    private final TrainingPlanService service;

    @PostMapping
    public ResponseEntity<TrainingPlanResponse> createPlan(Principal principal,
                                                           @Valid @RequestBody TrainingPlanRequest request) {
        return ResponseEntity.ok(service.create(principal, request));
    }

    @GetMapping
    public ResponseEntity<List<TrainingPlanResponse>> getPlans(Principal principal) {
        return ResponseEntity.ok(service.findAllForUser(principal));
    }

    @GetMapping("/{planId}")
    public ResponseEntity<TrainingPlanResponse> getPlan(Principal principal,
                                                        @PathVariable UUID planId) {
        return ResponseEntity.ok(service.getOne(principal, planId));
    }

    @PutMapping("/{planId}")
    public ResponseEntity<TrainingPlanResponse> updatePlan(Principal principal,
                                                           @PathVariable UUID planId,
                                                           @Valid @RequestBody TrainingPlanRequest request) {
        return ResponseEntity.ok(service.update(principal, planId, request));
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(Principal principal,
                                           @PathVariable UUID planId) {
        service.delete(principal, planId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{planId}/set-active")
    public ResponseEntity<TrainingPlanResponse> setActive(Principal principal,
                                                          @PathVariable UUID planId) {
        return ResponseEntity.ok(service.setActive(principal, planId));
    }
}
