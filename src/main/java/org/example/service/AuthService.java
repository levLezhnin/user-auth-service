package org.example.service;

import org.example.domain.User;
import org.example.web.dto.JwtRequestDTO;
import org.example.web.dto.RegistrationUserDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> createAuthToken(JwtRequestDTO authRequest);
    ResponseEntity<?> register(RegistrationUserDTO registrationUserDTO);
}
