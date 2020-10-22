package main.movie.service.impl;

import java.time.LocalDate;
import java.util.List;
import main.movie.dao.MovieSessionDao;
import main.movie.model.MovieSession;
import main.movie.service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    @Autowired
    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long id, LocalDate date) {
        return movieSessionDao.findAvailableSessions(id, date);
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.create(movieSession);
    }
}
