package ru.ob6.api.services;

import ru.ob6.api.dto.UserDto;
import ru.ob6.api.forms.EmailForm;
import ru.ob6.api.forms.ForgotPasswordForm;
import ru.ob6.api.forms.SignUpForm;
import ru.ob6.api.forms.UserDataForm;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<UserDto> userById(UUID id);
    Optional<UserDto> userByEmail(String email);
    Optional<UserDataForm> userDataByEmail(String email);
    void saveUser(SignUpForm signUpForm);
    boolean confirmEmail(UUID id);
    void saveNewUserData(UserDataForm userDataForm);
    void resetPassword(EmailForm emailForm);
    boolean changePassword(ForgotPasswordForm forgotPasswordForm);
}
