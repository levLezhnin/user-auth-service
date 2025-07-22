package org.example.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncrypter {

    private final PasswordEncoder passwordEncoder;

    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }
}
