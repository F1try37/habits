package io.github.f1try37.habits.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Введите логин")
        @Size(min = 3, message = "Не менее 3 символов")
        String username,

        @NotBlank(message = "Введите пароль")
        @Size(min = 8, message = "Не менее 8 символов")
        String password
) {}
