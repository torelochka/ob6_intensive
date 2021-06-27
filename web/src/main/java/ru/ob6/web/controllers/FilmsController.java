package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ob6.api.dto.UserDto;
import ru.ob6.api.services.FilmService;
import ru.ob6.api.services.UserService;

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
    public String getFilmsPage(Model model) {
        model.addAttribute("films", filmService.getAllFilms());
        System.out.println(filmService.getAllFilms());
        return "films";
    }
}
