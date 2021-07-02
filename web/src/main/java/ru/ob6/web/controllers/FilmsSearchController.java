package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ob6.api.services.FilmService;
import ru.ob6.impl.models.User;
import ru.ob6.web.forms.FilmSearchForm;
import ru.ob6.web.security.details.UserDetailsImpl;

import javax.validation.Valid;

@Controller
public class FilmsSearchController {

    private final FilmService filmService;

    @Autowired
    public FilmsSearchController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/films/search")
    public String searchFilms(@Valid FilmSearchForm searchForm, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("filmSearchForm", searchForm);

        if (userDetails != null && userDetails.getUser().getRole().equals(User.Role.ROLE_ADMIN)) {
            model.addAttribute("films", filmService.findFilms(searchForm.getSearch()));
        } else {
            model.addAttribute("films", filmService.findActiveFilms(searchForm.getSearch()));
        }
        model.addAttribute("search", true);
        return "films";
    }
}
