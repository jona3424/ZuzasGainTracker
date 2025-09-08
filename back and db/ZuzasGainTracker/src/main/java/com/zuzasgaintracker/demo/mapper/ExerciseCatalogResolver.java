package com.zuzasgaintracker.demo.mapper;

import com.zuzasgaintracker.demo.entity.ExerciseCatalog;
import com.zuzasgaintracker.demo.repository.ExerciseCatalogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ExerciseCatalogResolver {
    private final ExerciseCatalogRepository repo;
    public ExerciseCatalogResolver(ExerciseCatalogRepository repo) { this.repo = repo; }
    public ExerciseCatalog resolve(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ExerciseCatalog not found: " + id));
    }
}
