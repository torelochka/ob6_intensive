package ru.ob6.api.services;

import ru.ob6.api.dto.UserDto;
import ru.ob6.api.forms.SignUpForm;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<UserDto> userById(UUID id);
    Optional<UserDto> userByEmail(String email);
    void saveUser(SignUpForm signUpForm);
    boolean confirmEmail(UUID id);
}
