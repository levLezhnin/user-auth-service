package org.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO {
    private long id;
    private String roleName;

    public static Role toDomain(RoleDTO roleDTO) {
        return Role.builder()
                .id(roleDTO.getId())
                .roleName(roleDTO.getRoleName())
                .build();
    }

    public static RoleDTO toDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }
}
