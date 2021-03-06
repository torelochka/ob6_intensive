package ru.ob6.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ob6.impl.models.Film;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query(value = "SELECT f FROM Film f WHERE lower(f.title) LIKE :title")
    List<Film> getSearchFilms(@Param("title") String title);
}
