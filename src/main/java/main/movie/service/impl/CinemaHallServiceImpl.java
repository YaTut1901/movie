package main.movie.service.impl;

import java.util.List;
import main.movie.dao.CinemaHallDao;
import main.movie.model.CinemaHall;
import main.movie.service.CinemaHallService;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private final CinemaHallDao cinemaHallDao;

    public CinemaHallServiceImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.create(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
