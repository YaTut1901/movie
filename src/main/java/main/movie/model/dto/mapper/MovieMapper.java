package main.movie.model.dto.mapper;

import main.movie.model.Movie;
import main.movie.model.dto.MovieRequestDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public Movie map(MovieRequestDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        return movie;
    }

    public MovieRequestDto map(Movie movie) {
        return new MovieRequestDto(movie.getTitle(), movie.getDescription());
    }
}
