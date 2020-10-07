package main.movie.service;

import main.movie.model.CinemaHall;

import java.util.List;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
