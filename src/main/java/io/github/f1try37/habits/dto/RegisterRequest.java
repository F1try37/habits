package io.github.f1try37.habits.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank @Size(min = 3) String username, @NotBlank @Size(min = 8) String password) {}
