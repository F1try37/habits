package io.github.f1try37.habits.service;

import io.github.f1try37.habits.dto.HabitCreateRequest;
import io.github.f1try37.habits.dto.HabitResponse;
import io.github.f1try37.habits.entity.Habit;
import io.github.f1try37.habits.exceptions.HabitNotFoundException;
import io.github.f1try37.habits.repository.HabitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HabitServiceTest {

    @Mock
    private HabitRepository habitRepository;

    @InjectMocks
    private HabitService habitService;

    @Test
    void shouldCreateHabit() {
        //given
        HabitCreateRequest request = new HabitCreateRequest("Reading", "30 pages");

        Habit savedHabit = new Habit();
        savedHabit.setName("Reading");
        savedHabit.setDescription("30 pages");

        when(habitRepository.save(any(Habit.class))).thenReturn(savedHabit);

        //when
        HabitResponse result = habitService.create(request);

        //then
        assertEquals("Reading", result.name());
        assertEquals("30 pages", result.description());
        verify(habitRepository).save(any(Habit.class));
    }

    @Test
    void shouldThrowWhenHabitNotFound() {
        //given
        when(habitRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when + then
        HabitNotFoundException ex = assertThrows(HabitNotFoundException.class, () -> habitService.getById(1L));
        assertTrue(ex.getMessage().contains("1"));
    }
}
