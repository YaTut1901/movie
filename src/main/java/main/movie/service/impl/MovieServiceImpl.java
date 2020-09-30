package main.movie.service.impl;

import java.util.List;
import main.movie.dao.MovieDao;
import main.movie.lib.Inject;
import main.movie.lib.Service;
import main.movie.model.Movie;
import main.movie.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
