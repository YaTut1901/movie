package main.movie.dao;

import java.time.LocalDate;
import java.util.List;
import main.movie.model.MovieSession;

public interface MovieSessionDao {
    MovieSession create(MovieSession movieSession);

    MovieSession get(Long id);

    List<MovieSession> getAll();

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
