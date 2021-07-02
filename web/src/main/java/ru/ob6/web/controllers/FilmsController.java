package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ob6.api.dto.UserDto;
import ru.ob6.api.services.FilmService;
import ru.ob6.api.services.UserService;
import ru.ob6.impl.models.User;
import ru.ob6.web.forms.FilmSearchForm;
import ru.ob6.web.security.details.UserDetailsImpl;

import java.security.Principal;
import java.util.Optional;

@Controller
public class FilmsController {

    private final FilmService filmService;

    @Autowired
    public FilmsController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping({"/films", "/"})
    public String getFilmsPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("filmSearchForm", new FilmSearchForm());
        if (userDetails != null && userDetails.getUser().getRole().equals(User.Role.ROLE_ADMIN)) {
            model.addAttribute("films", filmService.getAllFilms());
        } else {
            model.addAttribute("films", filmService.getActiveFilms());
        }
        System.out.println(filmService.getAllFilms());
        return "films";
    }
}
