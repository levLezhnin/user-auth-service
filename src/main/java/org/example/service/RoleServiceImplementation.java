package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> getRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public Role getById(long roleId) {
        return roleRepository.getReferenceById(roleId);
    }

    @Override
    public List<Role> getUserRoles(long userId) {
        Optional<User> opt = userService.getUserById(userId);
        if (!opt.isPresent()) {
            throw new RuntimeException("No such user with id: " + userId);
        }
        return new ArrayList<>(opt.get().getRoles());
    }
}
