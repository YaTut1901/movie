package main.movie.service;

import java.util.List;
import main.movie.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
