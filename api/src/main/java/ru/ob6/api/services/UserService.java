package ru.ob6.api.services;

import ru.ob6.api.dto.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> userById(Long id);
}
