package org.example.web.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.example.service.AuthService;
import org.example.util.JwtTokenHelper;
import org.example.util.RevokedTokensHelper;
import org.example.web.dto.JwtRequestDTO;
import org.example.web.dto.RegistrationUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final Gson gson;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDTO authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationUserDTO registrationUserDTO) {
        return authService.register(registrationUserDTO);
    }

    @PostMapping("/admin/revoke")
    public ResponseEntity<?> revokeToken(@RequestBody String json) {
        Properties properties = gson.fromJson(json, Properties.class);
        String token = properties.getProperty("token");
        RevokedTokensHelper.revokeToken(token);
        return ResponseEntity.ok("Token revoked");
    }
}
