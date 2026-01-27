package com.example.StudyHub.security;

import com.example.StudyHub.entity.User;
import com.example.StudyHub.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword()) // must be BCrypt in DB
                .roles(user.getRole().name())        // like ADMIN or SUPERVISOR
                .build();
    }
}
