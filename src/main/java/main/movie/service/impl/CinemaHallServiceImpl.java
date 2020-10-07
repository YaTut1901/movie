package main.movie.service.impl;

import java.util.List;
import main.movie.dao.CinemaHallDao;
import main.movie.lib.Inject;
import main.movie.lib.Service;
import main.movie.model.CinemaHall;
import main.movie.service.CinemaHallService;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.create(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
