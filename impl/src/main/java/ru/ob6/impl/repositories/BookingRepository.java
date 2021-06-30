package ru.ob6.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ob6.impl.models.Booking;
import ru.ob6.impl.models.Seance;
import ru.ob6.impl.models.Seat;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query(value = "select exists(select * from booking where seance_id=:seanceId and seat_id=:seatId)", nativeQuery = true)
    Boolean existBookingBySeatAndSeance(@Param("seatId") Long seatId, @Param("seanceId") Long seanceId);
}