package io.github.f1try37.habits.exceptions;

import java.util.UUID;

public class HabitNotFoundException extends RuntimeException{

    public HabitNotFoundException(String name) {
        super("Habit not found with name: " + name);
    }
}
