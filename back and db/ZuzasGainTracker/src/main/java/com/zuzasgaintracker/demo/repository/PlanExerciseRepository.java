package com.zuzasgaintracker.demo.repository;

import com.zuzasgaintracker.demo.entity.PlanExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanExerciseRepository extends JpaRepository<PlanExercise, UUID> {}

