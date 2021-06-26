package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ob6.api.dto.FilmDto;
import ru.ob6.api.services.FilmService;

import java.util.Optional;

@Controller
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/film/{id}")
    public String getFilmPage(@PathVariable Long id, Model model) {
        Optional<FilmDto> filmDtoById = filmService.findById(id);

        if (filmDtoById.isPresent()) {
            model.addAttribute("film", filmDtoById.get());
        } else {
            model.addAttribute("film", null);
        }

        return "film";
    }
}
