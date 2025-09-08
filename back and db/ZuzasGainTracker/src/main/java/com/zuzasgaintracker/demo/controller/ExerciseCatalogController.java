package com.zuzasgaintracker.demo.controller;

import com.zuzasgaintracker.demo.dto.ExerciseCatalogDto;
import com.zuzasgaintracker.demo.service.ExerciseCatalogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/exercise-catalog")
public class ExerciseCatalogController {

    private final ExerciseCatalogService service;
    public ExerciseCatalogController(ExerciseCatalogService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ExerciseCatalogDto> create(@Valid @RequestBody ExerciseCatalogDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ExerciseCatalogDto>> all() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseCatalogDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseCatalogDto> update(@PathVariable UUID id, @Valid @RequestBody ExerciseCatalogDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
