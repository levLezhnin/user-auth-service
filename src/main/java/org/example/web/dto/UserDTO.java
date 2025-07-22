package org.example.web.dto;

import lombok.*;
import org.example.domain.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserDTO {
    private long id;
    private String name;
    private String email;


    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
