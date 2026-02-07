package com.authentication.Auth.service.impl;

import com.authentication.Auth.entity.User;
import com.authentication.Auth.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAndVerified(username, true)
                .orElseThrow(() -> new UsernameNotFoundException("User not found or not verified"));

        return user;
    }

    public User getUserByEmail(String username) {
        return userRepository.findByEmailAndVerified(username, true)
                .orElseThrow(() -> new UsernameNotFoundException("User not found or not verified"));
    }
}
