package io.github.f1try37.habits.dto;

import java.time.LocalDateTime;

public record HabitResponse(Long id, String name, String description, LocalDateTime createdAt){}

