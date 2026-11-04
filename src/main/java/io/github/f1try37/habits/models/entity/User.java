package io.github.f1try37.habits.models.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    public User() {}

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
