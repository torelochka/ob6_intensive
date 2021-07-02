package ru.ob6.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ob6.api.dto.SeanceDto;
import ru.ob6.api.dto.TakenSeats;
import ru.ob6.api.forms.SeanceForm;
import ru.ob6.api.services.SeanceService;
import ru.ob6.impl.models.Seance;
import ru.ob6.impl.repositories.FilmRepository;
import ru.ob6.impl.repositories.SeanceRepository;
import ru.ob6.impl.repositories.SeatRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository seanceRepository;
    private final FilmRepository filmRepository;
    private final SeatRepository seatRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SeanceServiceImpl(SeanceRepository seanceRepository, FilmRepository filmRepository, SeatRepository seatRepository, ModelMapper modelMapper) {
        this.seanceRepository = seanceRepository;
        this.filmRepository = filmRepository;
        this.seatRepository = seatRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createSeance(SeanceForm seanceForm) {
        Seance seance = Seance.builder()
                .date(seanceForm.getDate())
                .film(filmRepository.findById(seanceForm.getFilmId()).get())
                .build();

        seanceRepository.save(seance);
    }

    @Override
    public List<SeanceDto> findByFilmId(Long id) {
        Date date = new Date();
        return seanceRepository.findAllByFilmId(id).stream()
                .filter(s -> date.before(s.getDate()))
                .map(f -> modelMapper.map(f, SeanceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TakenSeats> getTakenSeanceSeats(Long seanceId) {
        return seatRepository.findTakenSeanceSeats(seanceId).stream()
                .map(seat -> new TakenSeats(seat.getRow() + ";" + seat.getColumn()))
                .collect(Collectors.toList());
    }

    @Override
    public SeanceDto findById(Long seanceId) {
        return seanceRepository.findById(seanceId)
                .map(seance -> modelMapper.map(seance, SeanceDto.class)).get();
    }
}
