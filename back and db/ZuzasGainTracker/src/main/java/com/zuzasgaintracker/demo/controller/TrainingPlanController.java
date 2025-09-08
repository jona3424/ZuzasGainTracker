package com.zuzasgaintracker.demo.controller;

import com.zuzasgaintracker.demo.dto.TrainingPlanRequest;
import com.zuzasgaintracker.demo.dto.TrainingPlanResponse;
import com.zuzasgaintracker.demo.service.TrainingPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class TrainingPlanController {

    private final TrainingPlanService service;

    @PostMapping
    public ResponseEntity<TrainingPlanResponse> create(
            @AuthenticationPrincipal(expression = "id") UUID userId,
            @Valid @RequestBody TrainingPlanRequest req) {
        return ResponseEntity.ok(service.create(userId, req));
    }

    @GetMapping
    public ResponseEntity<List<TrainingPlanResponse>> list(
            @AuthenticationPrincipal(expression = "id") UUID userId) {
        return ResponseEntity.ok(service.listMine(userId));
    }

    @GetMapping("/{planId}")
    public ResponseEntity<TrainingPlanResponse> get(
            @AuthenticationPrincipal(expression = "id") UUID userId,
            @PathVariable UUID planId) {
        return ResponseEntity.ok(service.get(userId, planId));
    }

    @PutMapping("/{planId}")
    public ResponseEntity<TrainingPlanResponse> update(
            @AuthenticationPrincipal(expression = "id") UUID userId,
            @PathVariable UUID planId,
            @Valid @RequestBody TrainingPlanRequest req) {
        return ResponseEntity.ok(service.update(userId, planId, req));
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal(expression = "id") UUID userId,
            @PathVariable UUID planId) {
        service.delete(userId, planId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{planId}/set-active")
    public ResponseEntity<TrainingPlanResponse> setActive(
            @AuthenticationPrincipal(expression = "id") UUID userId,
            @PathVariable UUID planId) {
        return ResponseEntity.ok(service.setActive(userId, planId));
    }
}
