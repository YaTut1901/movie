package main.movie.service;

import java.time.LocalDate;
import java.util.List;
import main.movie.model.MovieSession;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long id, LocalDate date);

    MovieSession add(MovieSession movieSession);
}
