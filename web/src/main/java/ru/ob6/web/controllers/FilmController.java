package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ob6.api.dto.FilmDto;
import ru.ob6.api.services.FilmService;
import ru.ob6.api.services.SeanceService;
import ru.ob6.impl.models.Film;

import java.util.Optional;

@Controller
public class FilmController {

    private final FilmService filmService;
    private final SeanceService seanceService;

    @Autowired
    public FilmController(FilmService filmService, SeanceService seanceService) {
        this.filmService = filmService;
        this.seanceService = seanceService;
    }

    @GetMapping("/film/{id}")
    public String getFilmPage(@PathVariable Long id, Model model, @RequestParam(name = "errorBooking", required = false) String error) {
        Optional<FilmDto> filmDtoById = filmService.findById(id);

        if (error != null) {
            model.addAttribute("errorBooking", "Выбранные места уже были забронированы");
        }

        if (filmDtoById.isPresent()) {
            model.addAttribute("film", filmDtoById.get());
            model.addAttribute("seances", seanceService.findByFilmId(id));
        } else {
            model.addAttribute("film", new FilmDto());
            model.addAttribute("error", "Фильм не найден");
        }

        return "film";
    }
}
