package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ob6.api.forms.UserDataForm;
import ru.ob6.api.services.UserService;

import javax.validation.Valid;

@Controller
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getCabinetPage(Model model, Authentication authentication) {
        model.addAttribute("userDataForm", userService.userDataByEmail(authentication.getName()));
        return "profile";
    }

    @PostMapping("/updateProfile")
    public String postUserData(@Valid UserDataForm userDataForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDataForm", userDataForm);
            return "profile";
        }
        else {
            userService.saveNewUserData(userDataForm);
            return "redirect:/profile";
        }
    }
}
