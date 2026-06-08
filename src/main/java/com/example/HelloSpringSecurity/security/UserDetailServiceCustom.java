package com.example.HelloSpringSecurity.security;

import com.example.HelloSpringSecurity.model.User;
import com.example.HelloSpringSecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceCustom implements UserDetailsService {

    private final UserRepository userRepository;

    // Constructor injection
    public UserDetailServiceCustom(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Return UserPrincipal (which contains user details and authorities)
        return UserPrincipal.create(user);
    }
}
