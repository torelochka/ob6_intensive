package ru.ob6.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ob6.api.dto.BookingDto;
import ru.ob6.api.services.BookingService;
import ru.ob6.impl.models.Booking;
import ru.ob6.impl.models.User;
import ru.ob6.impl.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookingServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookingDto> getAllBookingsByUserEmail(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            Set<Booking> bookings = optionalUser.get().getBookings();
            return bookings.stream().map(booking -> modelMapper.map(booking, BookingDto.class)).collect(Collectors.toList());
        }
        else return null;
    }
}
