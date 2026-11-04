package io.github.f1try37.habits.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record HabitCreateRequest(
        @NotBlank(message = "Введите имя")
        @Size(max = 100, message = "Не более 100 символов")
        @Pattern(regexp = "^[^/\\\\;%]*$", message = "Имя не может содержать символы / \\ ; %")
        String name,

        @Size(max = 2000, message = "Не более 2000 символов")
        String description
) {
}
