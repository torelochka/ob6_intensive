package ru.ob6.api.services;

import ru.ob6.api.dto.BookingDto;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    List<BookingDto> getAllBookingsByUserEmail(String email);
    Boolean bookingSeat(BookingDto booking, UUID user);
    void cancelBooking(UUID userId, Long bookingId);
    List<BookingDto> getAllViewedByUserEmail(String name);
}
