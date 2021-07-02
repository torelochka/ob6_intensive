package ru.ob6.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SignInController {

    @GetMapping("/signIn")
    public String getSignInPage(@RequestParam(value = "error", required = false) String error,
                                Model model, HttpServletRequest servletRequest, HttpSession session) {
        session.setAttribute("refererAuth", servletRequest.getHeader("referer"));
        if (error != null) {
            if (error.equals("credentials")) {
                model.addAttribute("error", "Почта или пароль введены неверно");
            } else if (error.equals("email")) {
                model.addAttribute("error", "Ваша почта не подтверждена");
            }
        }
        return "sign_in";
    }
}
