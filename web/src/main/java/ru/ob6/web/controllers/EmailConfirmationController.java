package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ob6.api.services.UserService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Controller
public class EmailConfirmationController {

    private final UserService userService;

    @Autowired
    public EmailConfirmationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/confirm/{code}")
    public String confirmEmail(@PathVariable @NotBlank @Size(max = 36) String code) {
        try {
            UUID id = UUID.fromString(code);
            if (userService.confirmEmail(id)) {
                return "redirect:/signIn";
            }
        }
        catch (IllegalArgumentException exception) {
            //TODO страничка ошибки подтверждения email
            return "redirect:/signIn";
        }
        //TODO страничка ошибки подтверждения email
        return "redirect:/signIn";
    }
}
