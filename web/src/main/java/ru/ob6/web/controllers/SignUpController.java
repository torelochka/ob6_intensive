package ru.ob6.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ob6.api.forms.SignUpForm;

@Controller
public class SignUpController {

    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "sign_up";
    }
}
