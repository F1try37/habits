package io.github.f1try37.habits.dto;

public record HabitResponse(
        String name,
        String description,
        int entriesCount
) {
}

