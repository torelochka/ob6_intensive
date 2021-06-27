package ru.ob6.api.services;

import ru.ob6.api.dto.BookingDto;

import java.util.List;

public interface BookingService {
    List<BookingDto> getAllBookingsByUserEmail(String email);
}
