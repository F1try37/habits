package io.github.f1try37.habits.repository;

import io.github.f1try37.habits.entity.Habit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
    @EntityGraph(attributePaths = "entries")
    List<Habit> findAllBy();
}
