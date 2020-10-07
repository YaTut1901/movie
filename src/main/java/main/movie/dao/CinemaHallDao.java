package main.movie.dao;

import main.movie.model.CinemaHall;

import java.util.List;

public interface CinemaHallDao {
    CinemaHall create(CinemaHall cinemaHall);

    CinemaHall get(Long id);

    List<CinemaHall> getAll();
}
