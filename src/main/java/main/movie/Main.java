package main.movie;

import main.movie.lib.Injector;
import main.movie.model.Movie;
import main.movie.service.MovieService;

public class Main {

    private static Injector injector = Injector.getInstance("main.movie");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
