package main.movie.dao;

import java.util.List;
import main.movie.model.Movie;

public interface MovieDao {

    Movie add(Movie movie);

    Movie get(Long id);

    List<Movie> getAll();
}
