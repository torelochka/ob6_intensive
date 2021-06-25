package ru.ob6.impl.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
@RequiredArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @NonNull
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @Transient
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return getName();
    }
}
