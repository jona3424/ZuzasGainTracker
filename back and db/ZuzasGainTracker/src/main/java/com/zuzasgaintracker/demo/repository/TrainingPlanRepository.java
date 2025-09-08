package com.zuzasgaintracker.demo.repository;

import com.zuzasgaintracker.demo.entity.TrainingPlan;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;
public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, UUID> {

    @Query("""
       select distinct p from TrainingPlan p
       left join fetch p.days d
       where p.user.id = :userId
       order by p.createdAt desc
       """)
    List<TrainingPlan> findAllDeepByUserId(@Param("userId") UUID userId);

    @Query("""
       select p from TrainingPlan p
       left join fetch p.days d
       where p.id = :planId and p.user.id = :userId
       """)
    Optional<TrainingPlan> findDeepByIdAndUserId(@Param("planId") UUID planId,
                                                 @Param("userId") UUID userId);


    List<TrainingPlan> findAllByUser_IdOrderByCreatedAtDesc(UUID userId);
}