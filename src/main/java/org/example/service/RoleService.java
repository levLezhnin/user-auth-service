package org.example.service;

import org.example.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> getRoleByRoleName(String roleName);
    Role getById(long roleId);
    List<Role> getUserRoles(long userId) throws Exception;
}
