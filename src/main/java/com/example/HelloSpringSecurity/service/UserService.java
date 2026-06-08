package com.example.HelloSpringSecurity.service;

import com.example.HelloSpringSecurity.dto.RegisterRequest;
import com.example.HelloSpringSecurity.model.User;
import com.example.HelloSpringSecurity.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user with an encoded password.
     * 
     * @param request the registration request DTO
     * @return the saved user entity
     */
    public User registerUser(RegisterRequest request) {
        // 1. Check if username already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username '" + request.getUsername() + "' is already taken!");
        }

        // 2. Hash/encode the plain password using PasswordEncoder
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 3. Create a new User entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword); // Store the hashed password!
        user.setFullName(request.getFullName());
        user.setRole("ROLE_USER"); // Default role
        user.setEnabled(true);

        // 4. Save to Database
        return userRepository.save(user);
    }
}
