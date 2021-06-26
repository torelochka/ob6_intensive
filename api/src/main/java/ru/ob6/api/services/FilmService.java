package ru.ob6.api.services;

import ru.ob6.api.dto.FilmDto;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<FilmDto> getAllFilms();

    Optional<FilmDto> findById(Long id);
}
