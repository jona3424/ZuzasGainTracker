package com.zuzasgaintracker.demo.repository;

import com.zuzasgaintracker.demo.entity.TrainingPlan;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, java.util.UUID> {

    List<TrainingPlan> findAllByUser_IdOrderByCreatedAtDesc(java.util.UUID userId);

    Optional<TrainingPlan> findByIdAndUser_Id(java.util.UUID id, java.util.UUID userId);

    @Modifying
    @Query("update TrainingPlan tp set tp.isActive = false where tp.user.id = :userId")
    void deactivateAllForUser(@Param("userId") UUID userId);
}
