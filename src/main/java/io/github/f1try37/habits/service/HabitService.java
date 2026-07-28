package io.github.f1try37.habits.service;

import io.github.f1try37.habits.dto.HabitCreateRequest;
import io.github.f1try37.habits.dto.HabitResponse;
import io.github.f1try37.habits.entity.Habit;
import io.github.f1try37.habits.repository.HabitRepository;
import org.springframework.stereotype.Service;

@Service
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public HabitResponse create(HabitCreateRequest request) {
        Habit habit = new Habit();
        habit.setName(request.name());
        habit.setDescription(request.description());
        Habit newHabit = habitRepository.save(habit);
        return new HabitResponse(newHabit.getId(), newHabit.getName(), newHabit.getDescription(), newHabit.getCreatedAt());
    }
}
