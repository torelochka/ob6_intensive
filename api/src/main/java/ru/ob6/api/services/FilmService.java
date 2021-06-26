package ru.ob6.api.services;

import ru.ob6.api.dto.FilmDto;

import java.util.List;

public interface FilmService {
    List<FilmDto> getAllFilms();
}
