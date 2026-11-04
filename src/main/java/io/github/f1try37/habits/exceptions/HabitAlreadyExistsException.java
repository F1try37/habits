package io.github.f1try37.habits.exceptions;

public class HabitAlreadyExistsException extends RuntimeException {

    public HabitAlreadyExistsException() {
        super("Habit with this name already exists");
    }
}
