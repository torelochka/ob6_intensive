package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ob6.api.forms.SeanceForm;
import ru.ob6.api.services.SeanceService;

import javax.validation.Valid;

@Controller
public class FilmSeanceController {

    private final SeanceService seanceService;

    @Autowired
    public FilmSeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping("/admin/film/{filmId}/seance")
    public String seanceCreatePage(@PathVariable Long filmId, Model model) {

        model.addAttribute("seanceForm", new SeanceForm());
        model.addAttribute("filmId", filmId);

        return "add_seance";
    }

    @PostMapping("/admin/film/{filmId}/seance")
    public String createSeance(@PathVariable Long filmId, @Valid SeanceForm seanceForm) {
        seanceForm.setFilmId(filmId);

        seanceService.createSeance(seanceForm);

        return "redirect:/film/" + filmId;
    }
}
