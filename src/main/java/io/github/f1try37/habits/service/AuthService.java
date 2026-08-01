package io.github.f1try37.habits.service;

import io.github.f1try37.habits.dto.AuthResponse;
import io.github.f1try37.habits.dto.LoginRequest;
import io.github.f1try37.habits.dto.RegisterRequest;
import io.github.f1try37.habits.dto.UserResponse;
import io.github.f1try37.habits.entity.User;
import io.github.f1try37.habits.exceptions.InvalidCredentialsException;
import io.github.f1try37.habits.exceptions.UsernameAlreadyExistsException;
import io.github.f1try37.habits.repository.UserRepository;
import io.github.f1try37.habits.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getCreatedAt());
    }

    public AuthService(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new UsernameAlreadyExistsException(request.username());
        }
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        User savedUser = userRepository.save(user);
        return toResponse(savedUser);
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username()).orElseThrow(InvalidCredentialsException::new);
        if (passwordEncoder.matches(request.password(), user.getPassword())) {
            String token = jwtService.generate(user.getUsername());
            return new AuthResponse(token);
        } else throw new InvalidCredentialsException();
    }
}
