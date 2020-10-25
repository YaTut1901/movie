package main.movie.model.dto.mapper;

import main.movie.model.Movie;
import main.movie.model.dto.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieResponseMapper {
    public Movie map(MovieResponseDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        return movie;
    }

    public MovieResponseDto map(Movie movie) {
        return new MovieResponseDto(movie.getTitle(), movie.getDescription());
    }
}
