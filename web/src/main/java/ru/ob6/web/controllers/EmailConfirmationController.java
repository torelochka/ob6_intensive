package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String confirmEmail(@PathVariable @NotBlank @Size(max = 36) String code, Model model) {
        try {
            UUID id = UUID.fromString(code);
            if (userService.confirmEmail(id)) {
                model.addAttribute("success", "Ваша почта подтверждена !");
                return "email_confirmed";
            } else {
                model.addAttribute("error", "Не удалось подтвердить почту, неверная ссылка");
                return "email_confirmed";
            }
        }
        catch (IllegalArgumentException exception) {
            model.addAttribute("error", "Не удалось подтвердить почту, неверная ссылка");
            return "email_confirmed";
        }
    }
}
