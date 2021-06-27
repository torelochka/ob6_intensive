package ru.ob6.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ob6.api.dto.SeanceDto;
import ru.ob6.api.forms.SeanceForm;
import ru.ob6.api.services.SeanceService;
import ru.ob6.impl.models.Seance;
import ru.ob6.impl.repositories.FilmRepository;
import ru.ob6.impl.repositories.SeanceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository seanceRepository;
    private final FilmRepository filmRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SeanceServiceImpl(SeanceRepository seanceRepository, FilmRepository filmRepository, ModelMapper modelMapper) {
        this.seanceRepository = seanceRepository;
        this.filmRepository = filmRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createSeance(SeanceForm seanceForm) {
        Seance seance = Seance.builder()
                .date(seanceForm.getDate())
                .film(filmRepository.getById(seanceForm.getFilmId()))
                .build();

        seanceRepository.save(seance);
    }

    @Override
    public List<SeanceDto> findByFilmId(Long id) {
        return seanceRepository.findAllByFilmId(id)
                .stream().map(f -> modelMapper.map(f, SeanceDto.class))
                .collect(Collectors.toList());
    }
}
