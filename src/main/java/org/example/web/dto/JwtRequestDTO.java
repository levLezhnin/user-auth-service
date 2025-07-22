package org.example.web.dto;

import lombok.Data;

@Data
public class JwtRequestDTO {
    private String name;
    private String password;
}
