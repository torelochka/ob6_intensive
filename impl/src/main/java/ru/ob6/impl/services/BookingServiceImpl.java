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

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookingServiceImpl(UserRepository userRepository, BookingRepository bookingRepository,
                              SeatRepository seatRepository, ModelMapper modelMapper) {
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
            Date date = new Date();
            return bookings.stream()
                    .filter(b -> date.before(b.getSeance().getDate()))
                    .map(booking -> modelMapper.map(booking, BookingDto.class)).collect(Collectors.toList());
        }
        else return new ArrayList<>();
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

    @Override
    public void cancelBooking(UUID userId, Long bookingId) {
        bookingRepository.findAllByIdAndUser(bookingId, User.builder().id(userId).build())
                .ifPresent(b -> bookingRepository.deleteById(bookingId));
    }

    @Override
    public List<BookingDto> getAllViewedByUserEmail(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            Set<Booking> bookings = bookingRepository.findBookingsByUser(optionalUser.get());
            Date date = new Date();
            return bookings.stream()
                    .filter(b -> date.after(b.getSeance().getDate()))
                    .map(booking -> modelMapper.map(booking, BookingDto.class)).collect(Collectors.toList());
        }
        else return new ArrayList<>();
    }
}
