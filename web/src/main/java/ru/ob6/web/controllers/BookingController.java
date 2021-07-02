package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ob6.api.dto.BookingDto;
import ru.ob6.api.dto.SeatDto;
import ru.ob6.api.services.BookingService;
import ru.ob6.api.services.SeanceService;
import ru.ob6.web.forms.SeatsForm;
import ru.ob6.web.security.details.UserDetailsImpl;
import ru.ob6.web.utils.SeatConverter;

import java.util.List;

@Controller
public class BookingController {

    private final BookingService bookingService;
    private final SeanceService seanceService;
    private final SeatConverter seatConverter;

    @Autowired
    public BookingController(BookingService bookingService, SeanceService seanceService, SeatConverter seatConverter) {
        this.bookingService = bookingService;
        this.seanceService = seanceService;
        this.seatConverter = seatConverter;
    }

    @PostMapping("/booking/{filmId}/seance/{seanceId}")
    public String bookingSeance(@PathVariable Long filmId, @PathVariable Long seanceId,
                                SeatsForm seats, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<SeatDto> convert = seatConverter.convert(seats);

        for (SeatDto seat: convert) {
            BookingDto booking = BookingDto.builder()
                    .seance(seanceService.findById(seanceId))
                    .seat(seat)
                    .build();

            if (!bookingService.bookingSeat(booking, userDetails.getUser().getId())) {
                return "redirect:/film/" + filmId + "?errorBooking=";
            }
        }

        return "redirect:/film/" + filmId;
    }
}
