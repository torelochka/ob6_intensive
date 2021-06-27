package ru.ob6.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ob6.impl.models.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

}
