package ru.ob6.web.utils;

import org.springframework.stereotype.Component;
import ru.ob6.api.dto.SeatDto;
import ru.ob6.web.forms.SeatsForm;

import java.util.List;

public interface SeatConverter {
    List<SeatDto> convert(SeatsForm seatsForm);
}
