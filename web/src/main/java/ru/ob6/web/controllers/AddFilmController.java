package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ob6.api.dto.FilmDto;
import ru.ob6.api.services.FilmService;
import ru.ob6.web.forms.FilmForm;
import ru.ob6.web.utils.FileSaver;
import ru.ob6.web.utils.FileSystemStorageService;
import ru.ob6.web.utils.TrailerUrlParser;

import javax.validation.Valid;

@Controller
public class AddFilmController {

    private final FilmService filmService;
    private final FileSystemStorageService storageService;

    /*@Value("${upload.dir.image}")
    private String posterUploadDir;*/

    @Autowired
    public AddFilmController(FilmService filmService, FileSystemStorageService storageService) {
        this.filmService = filmService;
        this.storageService = storageService;
    }

    @GetMapping("/admin/film")
    public String getFilmAddPage(Model model) {
        model.addAttribute("filmForm", new FilmForm());

        return "add_film";
    }

    @PostMapping("/admin/film")
    public String addFilm(@ModelAttribute @Valid FilmForm filmForm,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("filmForm", filmForm);
            return "add_film";
        }
        filmService.saveFilm(
                FilmDto.builder()
                        .title(filmForm.getTitle())
                        .description(filmForm.getDescription())
                        .posterName((storageService.store(filmForm.getPosterName())))
                        .trailerUrl(TrailerUrlParser.parse(filmForm.getTrailerUrl()))
                .build()
        );
        return "redirect:/films";
    }
}
