package io.github.f1try37.habits.controller;

import io.github.f1try37.habits.dto.HabitCreateRequest;
import io.github.f1try37.habits.dto.HabitEntryResponse;
import io.github.f1try37.habits.dto.HabitResponse;
import io.github.f1try37.habits.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<HabitResponse> create(
            @Valid @RequestBody HabitCreateRequest request,
            @AuthenticationPrincipal UserDetails user
            ) {
        HabitResponse response = habitService.create(user.getUsername(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<HabitResponse>> getAll(@AuthenticationPrincipal UserDetails user) {
        List<HabitResponse> responses = habitService.getAll(user.getUsername());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{name}")
    public ResponseEntity<HabitResponse> getByName(
            @PathVariable String name,
            @AuthenticationPrincipal UserDetails user
    ) {
        HabitResponse response = habitService.getByName(name, user.getUsername());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{name}")
    public ResponseEntity<HabitResponse> update(
            @PathVariable String name,
            @AuthenticationPrincipal UserDetails user,
            @Valid @RequestBody HabitCreateRequest request
    ) {
        HabitResponse response = habitService.update(name, user.getUsername(), request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(
            @PathVariable String name,
            @AuthenticationPrincipal UserDetails user
            ) {
        habitService.delete(name, user.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{name}/entries")
    public ResponseEntity<HabitEntryResponse> createEntry(
            @PathVariable String name,
            @AuthenticationPrincipal UserDetails user
            ) {
        HabitEntryResponse response = habitService.createEntry(name, user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
