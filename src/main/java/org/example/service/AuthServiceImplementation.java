package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.util.JwtTokenHelper;
import org.example.util.PasswordEncrypter;
import org.example.web.dto.JwtRequestDTO;
import org.example.web.dto.JwtResponse;
import org.example.web.dto.RegistrationUserDTO;
import org.example.web.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private final UserService userService;
    private final JwtTokenHelper jwtTokenHelper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncrypter passwordEncrypter;

    @Override
    public ResponseEntity<?> createAuthToken(JwtRequestDTO authRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getName());
        return ResponseEntity.ok(new JwtResponse(jwtTokenHelper.generateToken(userDetails)));
    }

    @Override
    public ResponseEntity<?> register(RegistrationUserDTO registrationUserDTO) {

        if (userService.getUserByName(registrationUserDTO.getName()).isPresent()) {
            return new ResponseEntity<>("User with provided username already exists.", HttpStatus.BAD_REQUEST);
        }
        if (!registrationUserDTO.getPassword().equals(registrationUserDTO.getConfirmPassword())) {
            return new ResponseEntity<>("Passwords doesn't match.", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(UserDTO.toDTO(userService.createNewUser(registrationUserDTO.getName(), passwordEncrypter.encrypt(registrationUserDTO.getPassword()), registrationUserDTO.getEmail())));
    }
}
