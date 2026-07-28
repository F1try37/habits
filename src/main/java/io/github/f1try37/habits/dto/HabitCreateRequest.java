package io.github.f1try37.habits.dto;

import jakarta.validation.constraints.NotBlank;

public record HabitCreateRequest(@NotBlank String name, String description) {}
