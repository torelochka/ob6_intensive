package ru.ob6.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ob6.impl.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
