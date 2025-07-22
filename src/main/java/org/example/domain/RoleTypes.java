package org.example.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum RoleTypes {
    GUEST("ROLE_guest", 1),
    PREMIUM_USER("ROLE_premium_user", 2),
    ADMIN("ROLE_admin", 3);

    private final long id;
    private final String roleName;

    public static RoleTypes getRoleTypeByRoleName(String roleName) {
        List<RoleTypes> types = Arrays.stream(RoleTypes.values()).filter(type -> type.getRoleName().equals(roleName)).collect(Collectors.toList());
        if (types.isEmpty()) {
            throw new RuntimeException("No such role with name: " + roleName);
        }
        return types.get(0);
    }

    RoleTypes(String roleName, long id) {
        this.roleName = roleName;
        this.id = id;
    }
}
