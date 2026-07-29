package io.github.f1try37.habits.controller;

import io.github.f1try37.habits.dto.HabitCreateRequest;
import io.github.f1try37.habits.dto.HabitResponse;
import io.github.f1try37.habits.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitResponse> create(@Valid @RequestBody HabitCreateRequest request) {
        HabitResponse response = habitService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<HabitResponse>> getAll() {
        List<HabitResponse> responses = habitService.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitResponse> getById(@PathVariable Long id) {
        HabitResponse response = habitService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitResponse> update(@PathVariable Long id, @Valid @RequestBody HabitCreateRequest request) {
        HabitResponse response = habitService.update(id,request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        habitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
