package com.officemanagement.office.service;

import com.officemanagement.office.dao.model.RoleEntity;
import com.officemanagement.office.dao.model.UserEntity;
import com.officemanagement.office.dao.repository.RoleRepository;
import com.officemanagement.office.dao.repository.UserRepository;
import com.officemanagement.office.dto.request.SignupRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public String signup(SignupRequestDTO request) {
        if (userRepository.existsByName(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Fetch role from DB
        RoleEntity role = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        UserEntity user = new UserEntity();
        user.setName(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        userRepository.save(user);
        return "User registered successfully";
    }

    public UserEntity authenticate(String username, String password) {
        UserEntity user = userRepository.findByName(username)
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
