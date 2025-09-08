package com.zuzasgaintracker.demo.service;

import com.zuzasgaintracker.demo.dto.ExerciseCatalogDto;
import com.zuzasgaintracker.demo.entity.ExerciseCatalog;
import com.zuzasgaintracker.demo.mapper.ExerciseCatalogMapper;
import com.zuzasgaintracker.demo.repository.ExerciseCatalogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service @Transactional
public class ExerciseCatalogService {

    private final ExerciseCatalogRepository repo;
    private final ExerciseCatalogMapper mapper;

    public ExerciseCatalogService(ExerciseCatalogRepository repo, ExerciseCatalogMapper mapper) {
        this.repo = repo; this.mapper = mapper;
    }

    public ExerciseCatalogDto create(ExerciseCatalogDto dto) {
        ExerciseCatalog saved = repo.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<ExerciseCatalogDto> findAll() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ExerciseCatalogDto get(UUID id) {
        return mapper.toDto(repo.findById(id).orElseThrow(() -> new EntityNotFoundException("ExerciseCatalog not found")));
    }

    public ExerciseCatalogDto update(UUID id, ExerciseCatalogDto dto) {
        ExerciseCatalog ec = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("ExerciseCatalog not found"));
        ec.setName(dto.getName());
        ec.setMuscleGroup(dto.getMuscleGroup());
        ec.setEquipment(dto.getEquipment());
        ec.setPattern(dto.getPattern());
        ec.setIsUnilateral(dto.getIsUnilateral());
        ec.setNotes(dto.getNotes());
        return mapper.toDto(ec);
    }

    public void delete(UUID id) { repo.deleteById(id); }
}
