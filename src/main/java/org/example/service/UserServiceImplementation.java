package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Role;
import org.example.domain.RoleTypes;
import org.example.domain.User;
import org.example.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createNewUser(String name, String passwordHash, String email) {
        User user = User.builder()
                .name(name)
                .email(email)
                .passwordHash(passwordHash)
                .roles(List.of(Role.builder()
                                .id(RoleTypes.GUEST.getId())
                                .roleName(RoleTypes.GUEST.getRoleName())
                                .build())
                )
                .build();
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.of(userRepository.getReferenceById(id));
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void assignNewRole(long id, String roleName) {
        Optional<User> opt = getUserById(id);
        RoleTypes roleType = RoleTypes.getRoleTypeByRoleName(roleName);

        if (!opt.isPresent()) {
            throw new RuntimeException("No such user with id: " + id);
        }

        User user = opt.get();
        if (user.getRoles().stream().anyMatch(role -> role.getRoleName().equals(roleName))) {
            throw new RuntimeException("User with id: " + id + " already has role: " + roleName);
        } else {
            user.getRoles().add(Role.builder().id(roleType.getId()).roleName(roleType.getRoleName()).build());
            userRepository.saveAndFlush(user);
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = getUserByName(username);
        if (!opt.isPresent()) {
            throw new UsernameNotFoundException(String.format("User '%s' not found.", username));
        }
        User user = opt.get();
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPasswordHash(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList())
        );
    }

}
