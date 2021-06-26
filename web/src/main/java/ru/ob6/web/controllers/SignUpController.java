package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ob6.api.dto.UserDto;
import ru.ob6.api.forms.SignUpForm;
import ru.ob6.api.services.MailService;
import ru.ob6.api.services.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class SignUpController {

    private final UserService userService;
    private final MailService mailService;

    @Autowired
    public SignUpController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "sign_up";
    }

    @PostMapping("/signUp")
    public String postSignInForm(@ModelAttribute("form") @Valid SignUpForm signUpForm,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "sign_up";
        }

        if (!signUpForm.getPassword().equals(signUpForm.getPasswordAgain())) {
            model.addAttribute("error", "Пароли не совпадают!");
            return "sign_up";
        }

        String email = signUpForm.getEmail();
        if (userService.userByEmail(email).isPresent()) {
            model.addAttribute("error", "Пользователь с таким email уже зарегестрирован!");
            return "sign_up";
        }

        userService.saveUser(signUpForm);

        Optional<UserDto> userOptional = userService.userByEmail(email);
        if (!userOptional.isPresent()) {
            model.addAttribute("error", "Упс...");
            return "sign_up";
        }

        mailService.sendEmailForConfirm(email, userOptional.get().getId().toString());

        return "redirect:/signIn";
    }
}
