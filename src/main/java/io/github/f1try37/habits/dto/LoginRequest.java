package io.github.f1try37.habits.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Введите логин")
        @Size(max = 100, message = "Не более 100 символов")
        String username,

        @NotBlank(message = "Введите пароль")
        String password
) {}
