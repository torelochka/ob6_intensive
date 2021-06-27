package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ob6.api.forms.SignUpForm;
import ru.ob6.api.services.SignUpService;

import javax.validation.Valid;

@Controller
public class SignUpController {

    private final SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
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
            model.addAttribute("signUpForm", signUpForm);
            return "sign_up";
        }

        if (!signUpForm.getPassword().equals(signUpForm.getPasswordAgain())) {
            model.addAttribute("error", "Пароли не совпадают!");
            model.addAttribute("signUpForm", signUpForm);
            return "sign_up";
        }

        if (!signUpService.signUp(signUpForm)) {
            model.addAttribute("error", "Пользователь с таким email уже зарегестрирован!");
            model.addAttribute("signUpForm", signUpForm);
            return "sign_up";
        }

        return "redirect:/signIn";
    }
}
