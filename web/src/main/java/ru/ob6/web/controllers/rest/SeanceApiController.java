package ru.ob6.web.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.ob6.api.dto.TakenSeats;
import ru.ob6.api.services.SeanceService;

import java.util.List;

@RestController
public class SeanceApiController {

    private final SeanceService seanceService;

    @Autowired
    public SeanceApiController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping("/api/v1/seance/{seanceId}")
    public List<TakenSeats> getSeanceSeats(@PathVariable Long seanceId) {
        return seanceService.getTakenSeanceSeats(seanceId);
    }
}
