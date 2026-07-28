package io.github.f1try37.habits.repository;

import io.github.f1try37.habits.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Long> {

}
