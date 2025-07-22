package com.officemanagement.office.controller;

import com.officemanagement.office.dto.request.SignupRequestDTO;
import com.officemanagement.office.dto.request.LoginRequestDTO;
import com.officemanagement.office.dto.response.LoginResponseDTO;
import com.officemanagement.office.service.AuthService;
import com.officemanagement.office.dao.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDTO request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        UserEntity user = authService.authenticate(request.getUsername(), request.getPassword());

        // Return a DTO with useful info; JWT token can be added later
        LoginResponseDTO response = new LoginResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().getRoleName(),
                "Login successful"
        );

        return ResponseEntity.ok(response);
    }
}
