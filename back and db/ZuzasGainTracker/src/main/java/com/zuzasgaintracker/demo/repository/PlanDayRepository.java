package com.zuzasgaintracker.demo.repository;

import com.zuzasgaintracker.demo.entity.PlanDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanDayRepository extends JpaRepository<PlanDay, UUID> {}

