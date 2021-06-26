package ru.ob6.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ob6.api.dto.FilmDto;
import ru.ob6.api.services.FilmService;
import ru.ob6.impl.repositories.FilmRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository, ModelMapper modelMapper) {
        this.filmRepository = filmRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FilmDto> getAllFilms() {
        return filmRepository.findAll().stream()
                .map(f -> modelMapper.map(f, FilmDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FilmDto> findById(Long id) {
        return filmRepository.findById(id)
                .map(f -> modelMapper.map(f, FilmDto.class));
    }
}
