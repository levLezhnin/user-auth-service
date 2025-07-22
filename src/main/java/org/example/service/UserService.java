package org.example.service;

import org.example.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User createNewUser(String name, String passwordHash, String email);

    Optional<User> getUserById(long id);
    Optional<User> getUserByName(String name);
    List<User> getAllUsers();

    void assignNewRole(long id, String roleName) throws Exception;
}
