package ru.ob6.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ob6.api.forms.SeanceForm;
import ru.ob6.api.services.SeanceService;
import ru.ob6.impl.models.Seance;
import ru.ob6.impl.repositories.FilmRepository;
import ru.ob6.impl.repositories.SeanceRepository;

@Service
public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository seanceRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public SeanceServiceImpl(SeanceRepository seanceRepository, FilmRepository filmRepository) {
        this.seanceRepository = seanceRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public void createSeance(SeanceForm seanceForm) {
        Seance seance = Seance.builder()
                .date(seanceForm.getDate())
                .film(filmRepository.getById(seanceForm.getFilmId()))
                .build();

        seanceRepository.save(seance);
    }
}
