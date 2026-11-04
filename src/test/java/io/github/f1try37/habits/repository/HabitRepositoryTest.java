package io.github.f1try37.habits.repository;

import io.github.f1try37.habits.models.entity.Habit;
import io.github.f1try37.habits.models.entity.HabitEntry;
import io.github.f1try37.habits.models.entity.User;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HabitRepositoryTest {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldCatchNPlus1() {
        //given
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("hash");
        entityManager.persist(user);

        Habit habit = new Habit();
        habit.setName("Reading");
        habit.setDescription("30 pages");
        habit.setUser(user);      // ← вот это
        entityManager.persist(habit);
        HabitEntry entry1 = new HabitEntry();
        entry1.setHabit(habit);
        HabitEntry entry2 = new HabitEntry();
        entry2.setHabit(habit);
        entry2.setCompletedAt(LocalDate.now().minusDays(1));
        entityManager.persist(entry1);
        entityManager.persist(entry2);
        entityManager.flush();
        UUID habitId = habit.getId();
        entityManager.clear();

        //when
        List<Habit> result = habitRepository.findAllByUserUsername();

        Habit found = result.stream()
                .filter(h -> h.getId().equals(habitId))
                .findFirst()
                .orElseThrow();

        //then
        assertTrue(Hibernate.isInitialized(found.getEntries()));
        assertEquals(2, found.getEntries().size());
    }
}
