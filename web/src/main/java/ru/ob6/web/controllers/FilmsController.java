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
    private final UserService userService;

    @Autowired
    public FilmsController(FilmService filmService, UserService userService) {
        this.filmService = filmService;
        this.userService = userService;
    }

    @GetMapping("/films")
    public String getFilmsPage(Principal principal, Model model) {

        String email = principal.getName();
        Optional<UserDto> userDtoOptional = userService.userByEmail(email);
        if (userDtoOptional.isPresent()) {
            model.addAttribute("user", userDtoOptional.get());
            model.addAttribute("films", filmService.getAllFilms());
            return "films";
        }
        //TODO страница ошибки
        return "films";
    }
}
