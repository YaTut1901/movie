package main.movie.service.impl;

import main.movie.dao.MovieSessionDao;
import main.movie.lib.Inject;
import main.movie.lib.Service;
import main.movie.model.MovieSession;
import main.movie.service.MovieSessionService;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;


    @Override
    public List<MovieSession> findAvailableSessions(Long id, LocalDate date) {
        return movieSessionDao.findAvailableSessions(id, date);
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.create(movieSession);
    }
}
