package org.example.web.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RegistrationUserDTO {

    private String name;
    private String password;
    private String confirmPassword;
    private String email;

}
