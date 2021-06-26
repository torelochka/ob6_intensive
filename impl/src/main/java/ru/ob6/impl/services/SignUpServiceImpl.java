package ru.ob6.impl.services;

import org.springframework.stereotype.Component;
import ru.ob6.api.forms.SignUpForm;
import ru.ob6.api.services.SignUpService;
import ru.ob6.api.services.UserService;

@Component
public class SignUpServiceImpl implements SignUpService {

    private final UserService userService;

    public SignUpServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /*TODO Мб возвращать не Boolean, а какую-нибудь обертку с полями успех и описание, чтобы на UI потом показать что за ошибка*/
    @Override
    public Boolean signUp(SignUpForm signUpForm) {
        boolean isUserAlreadyExists = userService.userByEmail(signUpForm.getEmail()).isPresent();
        boolean isPasswordsEquals = signUpForm.getPassword().equals(signUpForm.getPasswordAgain());
        if (!isUserAlreadyExists || !isPasswordsEquals) {
            userService.saveUser(signUpForm);
        }
        return isUserAlreadyExists || isPasswordsEquals;
    }
}
