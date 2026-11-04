package io.github.f1try37.habits.repository;

import io.github.f1try37.habits.models.entity.HabitEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HabitEntryRepository extends JpaRepository<HabitEntry, UUID> {
}
