package ru.ob6.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ob6.impl.models.Booking;
import ru.ob6.impl.models.User;

import java.util.Set;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Set<Booking> findBookingsByUser(User user);
}
