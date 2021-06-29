package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        String email = authentication.getName();
        model.addAttribute("userDataForm", userService.userByEmail(email));
        // TODO: 28.06.2021 брать букинги из юзера 
        model.addAttribute("bookings", bookingService.getAllBookingsByUserEmail(email));
        return "profile";
    }

    @PostMapping("/updateProfile")
    public String postUserData(@ModelAttribute @Valid UserDataForm userDataForm) {
        userService.saveNewUserData(userDataForm);
        return "redirect:/profile";
    }
}
