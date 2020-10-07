package main.movie.dao;

import java.util.List;
import main.movie.model.Movie;

public interface MovieDao {

    Movie add(Movie movie);

    List<Movie> getAll();
}
