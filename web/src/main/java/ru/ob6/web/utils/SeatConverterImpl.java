package ru.ob6.web.utils;

import org.springframework.stereotype.Component;
import ru.ob6.api.dto.SeatDto;
import ru.ob6.web.forms.SeatsForm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SeatConverterImpl implements SeatConverter {

    @Override
    public List<SeatDto> convert(SeatsForm seatsForm) {
        List<SeatDto> seatDtos = new ArrayList<>();
        System.out.println(seatsForm);
        for (String seat : seatsForm.getSeat()) {
            String[] splitSeat = seat.split(";");
            System.out.println(Arrays.toString(splitSeat));
            seatDtos.add(new SeatDto(Integer.parseInt(splitSeat[0]), Integer.parseInt(splitSeat[1])));
        }

        return seatDtos;
    }
}
