package ru.ob6.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ob6.api.dto.BookingDto;
import ru.ob6.api.services.BookingService;
import ru.ob6.impl.models.Booking;
import ru.ob6.impl.models.Seance;
import ru.ob6.impl.models.Seat;
import ru.ob6.impl.models.User;
import ru.ob6.impl.repositories.BookingRepository;
import ru.ob6.impl.repositories.SeatRepository;
import ru.ob6.impl.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookingServiceImpl(UserRepository userRepository, BookingRepository bookingRepository, SeatRepository seatRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookingDto> getAllBookingsByUserEmail(String email) {

        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            Set<Booking> bookings = bookingRepository.findBookingsByUser(optionalUser.get());
            return bookings.stream().map(booking -> modelMapper.map(booking, BookingDto.class)).collect(Collectors.toList());
        }
        else return null;
    }

    @Override
    public Boolean bookingSeat(BookingDto booking, UUID user) {
        Seat seat = seatRepository.findAllByRowAndColumn(booking.getSeat().getRow(), booking.getSeat().getColumn());
        Seance seance = modelMapper.map(booking.getSeance(), Seance.class);
        if (!bookingRepository.existBookingBySeatAndSeance(seat.getId(), seance.getId())) {
            Booking newBooking = Booking.builder()
                    .seance(seance)
                    .seat(seat)
                    .user(userRepository.findUserById(user).get())
                    .build();

            bookingRepository.save(newBooking);
            return true;
        }
        return false;
    }
}
