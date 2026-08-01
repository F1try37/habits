package io.github.f1try37.habits.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("User with username: " + username + " already exists");
    }
}
