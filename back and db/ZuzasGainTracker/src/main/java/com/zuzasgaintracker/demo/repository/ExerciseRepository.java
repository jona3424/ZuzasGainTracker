package com.zuzasgaintracker.demo.repository;

import com.zuzasgaintracker.demo.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ExerciseRepository extends JpaRepository<Exercise, java.util.UUID> {
    List<Exercise> findAllByTrainingPlan_IdOrderBySequenceOrder(java.util.UUID planId);
}
