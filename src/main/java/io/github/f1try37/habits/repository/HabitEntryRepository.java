package io.github.f1try37.habits.repository;

import io.github.f1try37.habits.entity.HabitEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitEntryRepository extends JpaRepository<HabitEntry, Long> {
}
