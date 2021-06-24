package ru.ob6.impl.repositories;

import org.springframework.stereotype.Repository;
import ru.ob6.impl.models.User;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
}
