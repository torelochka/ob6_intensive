package ru.ob6.impl.services;

import org.springframework.stereotype.Component;
import ru.ob6.api.forms.SignUpForm;
import ru.ob6.api.services.MailService;
import ru.ob6.api.services.SignUpService;
import ru.ob6.api.services.UserService;
import ru.ob6.impl.repositories.UserRepository;

@Component
public class SignUpServiceImpl implements SignUpService {

    private final UserService userService;

    public SignUpServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Boolean signUp(SignUpForm signUpForm) {
        boolean isUserAlreadyExists = userService.userByEmail(signUpForm.getEmail()).isPresent();
        if (!isUserAlreadyExists) {
            userService.saveUser(signUpForm);
        }
        return !isUserAlreadyExists;
    }
}
