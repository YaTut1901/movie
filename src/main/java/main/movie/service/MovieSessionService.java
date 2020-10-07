package main.movie.service;

import main.movie.model.MovieSession;

import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long id, LocalDate date);

    MovieSession add(MovieSession movieSession);
}
