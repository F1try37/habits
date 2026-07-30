package io.github.f1try37.habits.dto;

import java.time.LocalDate;

public record HabitEntryResponse(Long id, LocalDate completedAt, Long habitId) {}
