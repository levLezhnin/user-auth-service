package org.example.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role_name", unique = true)
    private String roleName;
}
