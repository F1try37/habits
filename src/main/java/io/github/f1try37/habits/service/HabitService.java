package io.github.f1try37.habits.service;

import io.github.f1try37.habits.dto.HabitCreateRequest;
import io.github.f1try37.habits.dto.HabitResponse;
import io.github.f1try37.habits.entity.Habit;
import io.github.f1try37.habits.exceptions.HabitNotFoundException;
import io.github.f1try37.habits.repository.HabitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    private HabitResponse toResponse(Habit habit) {
        return new HabitResponse(habit.getId(), habit.getName(), habit.getDescription(), habit.getCreatedAt());
    }

    @Transactional
    public HabitResponse create(HabitCreateRequest request) {
        Habit habit = new Habit();
        habit.setName(request.name());
        habit.setDescription(request.description());
        Habit newHabit = habitRepository.save(habit);
        return toResponse(newHabit);
    }

    public List<HabitResponse> getAll() {
        List<Habit> habits = habitRepository.findAll();
        return habits.stream().map(this::toResponse).toList();
    }

    public HabitResponse getById(Long id) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new HabitNotFoundException(id));
        return toResponse(habit);
    }

    @Transactional
    public HabitResponse update(Long id, HabitCreateRequest request) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new HabitNotFoundException(id));
        habit.setName(request.name());
        habit.setDescription(request.description());
        return toResponse(habit);
    }

    @Transactional
    public void delete(Long id) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new HabitNotFoundException(id));
        habitRepository.delete(habit);
    }
}
