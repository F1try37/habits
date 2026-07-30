package io.github.f1try37.habits.service;

import io.github.f1try37.habits.dto.HabitCreateRequest;
import io.github.f1try37.habits.dto.HabitEntryResponse;
import io.github.f1try37.habits.dto.HabitResponse;
import io.github.f1try37.habits.entity.Habit;
import io.github.f1try37.habits.entity.HabitEntry;
import io.github.f1try37.habits.exceptions.HabitNotFoundException;
import io.github.f1try37.habits.repository.HabitEntryRepository;
import io.github.f1try37.habits.repository.HabitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HabitService {

    private final HabitRepository habitRepository;
    private final HabitEntryRepository habitEntryRepository;

    public HabitService(HabitRepository habitRepository, HabitEntryRepository habitEntryRepository) {
        this.habitRepository = habitRepository;
        this.habitEntryRepository = habitEntryRepository;
    }

    private HabitResponse toResponse(Habit habit) {
        return new HabitResponse(habit.getId(), habit.getName(), habit.getDescription(), habit.getCreatedAt(), habit.getEntries().size());
    }

    @Transactional
    public HabitResponse create(HabitCreateRequest request) {
        Habit habit = new Habit();
        habit.setName(request.name());
        habit.setDescription(request.description());
        Habit savedHabit = habitRepository.save(habit);
        return toResponse(savedHabit);
    }

    public List<HabitResponse> getAll() {
        List<Habit> habits = habitRepository.findAllBy();
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

    @Transactional
    public HabitEntryResponse createEntry(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new HabitNotFoundException(habitId));
        HabitEntry entry = new HabitEntry();
        entry.setHabit(habit);
        HabitEntry savedEntry = habitEntryRepository.save(entry);
        return new HabitEntryResponse(savedEntry.getId(), savedEntry.getCompletedAt(), habitId);
    }
}
