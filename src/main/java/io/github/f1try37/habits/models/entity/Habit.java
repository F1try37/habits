package io.github.f1try37.habits.models.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "habits",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_user_id_name",
                columnNames = {"user_id","name"}
        )
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Habit extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @OneToMany(
            mappedBy = "habit",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<HabitEntry> entries = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Habit() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public List<HabitEntry> getEntries() {
        return entries;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
