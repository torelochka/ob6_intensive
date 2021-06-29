package ru.ob6.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ob6.impl.models.Seat;

import java.util.Arrays;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query(value = "select * from seat s where s.id in (select b.seat_id from booking b where seance_id = :seanceId) order by seat, row", nativeQuery = true)
    List<Seat> findTakenSeanceSeats(@Param("seanceId") Long seanceId);
}
