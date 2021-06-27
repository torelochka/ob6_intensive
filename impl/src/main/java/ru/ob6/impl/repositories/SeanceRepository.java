package ru.ob6.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ob6.impl.models.Seance;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

}
