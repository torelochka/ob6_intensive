package ru.ob6.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ob6.api.services.FilmService;

@Controller
public class FilmRentController {

    private final FilmService filmService;

    public FilmRentController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/admin/film/{id}/rentOff")
    public String takeRentOff(@PathVariable Long id) {
        filmService.rentOff(id);

        return "redirect:/film/" + id;
    }

    @GetMapping("/admin/film/{id}/rentOn")
    public String takeRentOn(@PathVariable Long id) {
        filmService.rentOn(id);

        return "redirect:/film/" + id;
    }
}
