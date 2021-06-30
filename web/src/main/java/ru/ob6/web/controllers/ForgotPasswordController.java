package ru.ob6.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ob6.api.dto.UserDto;
import ru.ob6.api.forms.EmailForm;
import ru.ob6.api.forms.ForgotPasswordForm;
import ru.ob6.api.services.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ForgotPasswordController {

    private final UserService userService;

    public ForgotPasswordController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/repass")
    public String getForgotPasswordEmailPage(Model model) {
        model.addAttribute("emailForm", new EmailForm());
        return "reset_password";
    }

    @GetMapping("/repass/{code}")
    public String getForgotPasswordPage(@PathVariable @NotBlank @Size(max = 36) String code, Model model) {
        try {
            UUID id = UUID.fromString(code);
            Optional<UserDto> userDtoOptional = userService.userById(id);
            if (userDtoOptional.isPresent()) {
                model.addAttribute("forgotPasswordForm",
                        ForgotPasswordForm.builder()
                                .email(userDtoOptional.get().getEmail()).build()
                );
                return "password_change";
            } else {
                model.addAttribute("error", "Не удалось измменить пароль. Неверная ссылка!");
            }
        } catch (IllegalArgumentException exception) {
            model.addAttribute("error", "Не удалось измменить пароль. Неверная ссылка!");
        }
        return "redirect:/signIn";
    }

    @PostMapping("/repass/email")
    public String resetPasswordPost(@Valid EmailForm emailForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("emailForm", emailForm);
            return "reset_password";
        } else {
            Optional<UserDto> userDtoOptional = userService.userByEmail(emailForm.getEmail());
            if (userDtoOptional.isPresent()) {
                userService.resetPassword(emailForm);
                model.addAttribute("email", emailForm.getEmail());
                return "password_cleared";
            } else {
                model.addAttribute("error", "Пользователь с таким email не зарегистрирован!");
                return "reset_password";
            }
        }
    }

    @PostMapping("/repass")
    public String resetPasswordPost(@Valid ForgotPasswordForm forgotPasswordForm,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("forgotPasswordForm", forgotPasswordForm);
            return "password_change";
        } else {
            Optional<UserDto> userDtoOptional = userService.userByEmail(forgotPasswordForm.getEmail());
            if (userDtoOptional.isPresent()) {
                userService.changePassword(forgotPasswordForm);
                model.addAttribute("success", true);
            } else {
                model.addAttribute("error", "Произошла ошибка восстановления пароля, попробуйте еще раз.");
            }
            return "password_changed";
        }
    }

}
