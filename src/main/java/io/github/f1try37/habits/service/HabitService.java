package io.github.f1try37.habits.service;

import io.github.f1try37.habits.dto.HabitCreateRequest;
import io.github.f1try37.habits.dto.HabitEntryResponse;
import io.github.f1try37.habits.dto.HabitResponse;
import io.github.f1try37.habits.exceptions.HabitAlreadyExistsException;
import io.github.f1try37.habits.models.entity.Habit;
import io.github.f1try37.habits.models.entity.HabitEntry;
import io.github.f1try37.habits.exceptions.HabitNotFoundException;
import io.github.f1try37.habits.models.entity.User;
import io.github.f1try37.habits.repository.HabitEntryRepository;
import io.github.f1try37.habits.repository.HabitRepository;
import io.github.f1try37.habits.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HabitService {

    private final HabitRepository habitRepository;
    private final HabitEntryRepository habitEntryRepository;
    private final UserRepository userRepository;

    public HabitService(HabitRepository habitRepository, HabitEntryRepository habitEntryRepository, UserRepository userRepository) {

        this.habitRepository = habitRepository;
        this.habitEntryRepository = habitEntryRepository;
        this.userRepository = userRepository;
    }

    private HabitResponse toResponse(Habit habit) {
        return new HabitResponse(habit.getName(), habit.getDescription(), habit.getEntries().size());
    }

    @Transactional
    public HabitResponse create(String username, HabitCreateRequest request) {
        String newName = request.name().trim();
        if (habitRepository.existsByNameAndUserUsername(newName, username)) throw new HabitAlreadyExistsException();
        User user = userRepository.findByUsername(username).orElseThrow();
        Habit habit = new Habit();
        habit.setName(newName);
        habit.setDescription(request.description());
        habit.setUser(user);
        Habit savedHabit = habitRepository.save(habit);
        return toResponse(savedHabit);
    }

    public List<HabitResponse> getAll(String username) {
        List<Habit> habits = habitRepository.findAllByUserUsername(username);
        return habits.stream().map(this::toResponse).toList();
    }

    public HabitResponse getByName(String name, String username) {
        Habit habit = habitRepository.findByNameAndUserUsername(name, username).orElseThrow(() -> new HabitNotFoundException(name));
        return toResponse(habit);
    }

    @Transactional
    public HabitResponse update(String name, String username, HabitCreateRequest request) {
        String newName = request.name().trim();
        Habit habit = habitRepository.findByNameAndUserUsername(name, username).orElseThrow(() -> new HabitNotFoundException(name));
        if (!name.equals(newName) && habitRepository.existsByNameAndUserUsername(newName, username)) throw new HabitAlreadyExistsException();
        habit.setName(newName);
        habit.setDescription(request.description());
        return toResponse(habit);
    }

    @Transactional
    public void delete(String name, String username) {
        Habit habit = habitRepository.findByNameAndUserUsername(name, username).orElseThrow(() -> new HabitNotFoundException(name));
        habitRepository.delete(habit);
    }

    @Transactional
    public HabitEntryResponse createEntry(String name, String username) {
        Habit habit = habitRepository.findByNameAndUserUsername(name, username).orElseThrow(() -> new HabitNotFoundException(name));
        HabitEntry entry = new HabitEntry();
        entry.setHabit(habit);
        HabitEntry savedEntry = habitEntryRepository.save(entry);
        return new HabitEntryResponse(savedEntry.getCompletedAt(), name);
    }
}
