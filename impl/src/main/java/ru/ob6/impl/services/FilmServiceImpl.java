package ru.ob6.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ob6.api.dto.FilmDto;
import ru.ob6.api.services.FilmService;
import ru.ob6.impl.models.Film;
import ru.ob6.impl.models.Seance;
import ru.ob6.impl.repositories.FilmRepository;
import ru.ob6.impl.repositories.SeanceRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final SeanceRepository seanceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository, SeanceRepository seanceRepository, ModelMapper modelMapper) {
        this.filmRepository = filmRepository;
        this.seanceRepository = seanceRepository;
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

    @Override
    public void saveFilm(FilmDto filmDto) {
        filmRepository.save(
                Film.builder()
                        .title(filmDto.getTitle())
                        .description(filmDto.getDescription())
                        .posterName(filmDto.getPosterName())
                        .trailerUrl(filmDto.getTrailerUrl())
                .build()
        );
    }

    @Override
    public void rentOn(Long id) {
        Optional<Film> byId = filmRepository.findById(id);
        byId.ifPresent(film -> {
            film.setInRent(true);
            filmRepository.save(byId.get());
        });

    }

    @Override
    public void rentOff(Long id) {
        Optional<Film> byId = filmRepository.findById(id);
        byId.ifPresent(film -> {
            film.setInRent(false);
            filmRepository.save(byId.get());
        });
    }

    @Override
    public List<FilmDto> findFilms(String search) {
        return filmRepository.getSearchFilms("%" + search.toLowerCase() + "%").stream()
                .map(f -> modelMapper.map(f, FilmDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<FilmDto> findActiveFilms(String search) {
        List<Film> films = filmRepository.getSearchFilms("%" + search.toLowerCase() + "%");

        return filterFilms(films);
    }

    @Override
    public List<FilmDto> getActiveFilms() {
        List<Film> films = filmRepository.findAll();

        return filterFilms(films);
    }

    private List<FilmDto> filterFilms(List<Film> films) {
        List<FilmDto> filmDtos = new ArrayList<>();
        Date date = new Date();
        for (Film film : films) {
            List<Seance> seances = seanceRepository.findAllByFilmId(film.getId()).stream()
                    .filter(s -> date.before(s.getDate()))
                    .collect(Collectors.toList());
            if (!seances.isEmpty()) {
                filmDtos.add(modelMapper.map(film, FilmDto.class));
            }
        }

        return filmDtos;
    }
}
