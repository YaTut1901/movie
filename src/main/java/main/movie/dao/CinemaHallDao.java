package main.movie.dao;

import java.util.List;
import main.movie.model.CinemaHall;

public interface CinemaHallDao {
    CinemaHall create(CinemaHall cinemaHall);

    CinemaHall get(Long id);

    List<CinemaHall> getAll();
}
