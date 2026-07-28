package io.github.f1try37.habits.exceptions;

public class HabitNotFoundException extends RuntimeException{

    public HabitNotFoundException(Long id) {
        super("Habit not found with id: " + id);
    }
}
