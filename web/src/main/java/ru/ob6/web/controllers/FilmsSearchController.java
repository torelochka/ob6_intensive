package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ob6.api.services.FilmService;
import ru.ob6.web.forms.FilmSearchForm;

import javax.validation.Valid;

@Controller
public class FilmsSearchController {

    private final FilmService filmService;

    @Autowired
    public FilmsSearchController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/films/search")
    public String searchFilms(@Valid FilmSearchForm searchForm, Model model) {
        model.addAttribute("filmSearchForm", searchForm);
        model.addAttribute("films", filmService.findFilms(searchForm.getSearch()));
        model.addAttribute("search", true);
        return "films";
    }
}
