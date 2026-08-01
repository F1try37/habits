package io.github.f1try37.habits.dto;

import java.time.LocalDateTime;

public record UserResponse(Long id, String username, LocalDateTime createdAt) {}
