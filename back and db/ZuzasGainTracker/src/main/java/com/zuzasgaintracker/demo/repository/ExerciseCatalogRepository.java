package com.zuzasgaintracker.demo.repository;

import com.zuzasgaintracker.demo.entity.ExerciseCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseCatalogRepository extends JpaRepository<ExerciseCatalog, UUID> {}

