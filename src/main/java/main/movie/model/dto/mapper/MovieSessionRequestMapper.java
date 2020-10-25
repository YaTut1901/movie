package main.movie.model.dto.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import main.movie.model.MovieSession;
import main.movie.model.dto.MovieSessionRequestDto;
import main.movie.service.CinemaHallService;
import main.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionRequestMapper {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    @Autowired
    public MovieSessionRequestMapper(MovieService movieService,
                                     CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSession map(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime(),
                FORMATTER));
        movieSession.setMovie(movieService.get(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionRequestDto.getCinemaHallId()));
        return movieSession;
    }

    public MovieSessionRequestDto map(MovieSession movieSession) {
        return new MovieSessionRequestDto(movieSession.getMovie().getId(),
                movieSession.getCinemaHall().getId(),
                movieSession.getShowTime().format(FORMATTER));
    }
}
