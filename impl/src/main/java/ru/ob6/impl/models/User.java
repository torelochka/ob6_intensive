package ru.ob6.impl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String firstName;
    private String town;

    private String password;

    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private Role role = Role.ROLE_USER;

    private enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    public boolean isAdmin() { return this.role == Role.ROLE_ADMIN; }
}
