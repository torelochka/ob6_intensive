package ru.ob6.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ob6.impl.models.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
}
