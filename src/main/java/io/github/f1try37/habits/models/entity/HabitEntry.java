package io.github.f1try37.habits.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
      name = "habit_entry",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_habit_entry_habit_date",
                columnNames = {"habit_id", "completed_at"}
        )
)
public class HabitEntry extends BaseEntity {

    @Column(name = "completed_at",nullable = false)
    private LocalDate completedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    public HabitEntry() {}


    public LocalDate getCompletedAt() {
        return completedAt;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public void setCompletedAt(LocalDate completedAt) {
        this.completedAt = completedAt;
    }

    @PrePersist
    protected void onCreate() {
        if (completedAt == null) {
            completedAt = LocalDate.now();
        }
    }
}
