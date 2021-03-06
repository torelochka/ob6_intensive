package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ob6.api.forms.UserDataForm;
import ru.ob6.api.services.BookingService;
import ru.ob6.api.services.UserService;

import javax.validation.Valid;

@Controller
public class ProfileController {

    private final UserService userService;
    private final BookingService bookingService;

    @Autowired
    public ProfileController(UserService userService, BookingService bookingService) {
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping("/profile")
    public String getCabinetPage(Model model, Authentication authentication) {
        model.addAttribute("userDataForm", userService.userDataByEmail(authentication.getName()));
        model.addAttribute("bookings", bookingService.getAllBookingsByUserEmail(authentication.getName()));
        model.addAttribute("vieweds", bookingService.getAllViewedByUserEmail(authentication.getName()));
        return "profile";
    }

    @PostMapping("/updateProfile")
    public String postUserData(@Valid UserDataForm userDataForm, BindingResult bindingResult,
                               Model model,  Authentication authentication) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDataForm", userDataForm);
            model.addAttribute("bookings", bookingService.getAllBookingsByUserEmail(authentication.getName()));
            model.addAttribute("vieweds", bookingService.getAllViewedByUserEmail(authentication.getName()));
            return "profile";
        }
        else {
            userService.saveNewUserData(userDataForm);
            return "redirect:/profile";
        }
    }
}
