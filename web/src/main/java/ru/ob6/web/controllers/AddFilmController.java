package ru.ob6.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ob6.web.forms.FilmForm;

@Controller
public class AddFilmController {

    @GetMapping("/admin/film")
    public String getFilmAddPage(Model model) {
        model.addAttribute("filmForm", new FilmForm());

        return "add_film";
    }

    // TODO: при добавлении фильма, забирать с ссылки на трейлер лишь последнюю часть
}
