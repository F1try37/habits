package io.github.f1try37.habits.repository;

import io.github.f1try37.habits.models.entity.Habit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HabitRepository extends JpaRepository<Habit, UUID> {
    @EntityGraph(attributePaths = "entries")
    List<Habit> findAllByUserUsername(String username);

    Optional<Habit> findByNameAndUserUsername(String name, String username);

    boolean existsByNameAndUserUsername(String name, String username);
}
