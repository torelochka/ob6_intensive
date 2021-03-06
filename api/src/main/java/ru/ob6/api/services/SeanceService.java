package ru.ob6.api.services;

import ru.ob6.api.dto.SeanceDto;
import ru.ob6.api.dto.TakenSeats;
import ru.ob6.api.forms.SeanceForm;

import java.util.List;

public interface SeanceService {
    void createSeance(SeanceForm seanceForm);

    List<SeanceDto> findByFilmId(Long id);

    List<TakenSeats> getTakenSeanceSeats(Long seanceId);

    SeanceDto findById(Long seanceId);
}
