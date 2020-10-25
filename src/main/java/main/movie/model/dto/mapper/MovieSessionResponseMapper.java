package main.movie.model.dto.mapper;

import java.time.format.DateTimeFormatter;
import main.movie.model.MovieSession;
import main.movie.model.dto.MovieSessionResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionResponseMapper {

    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ISO_DATE_TIME;

    public MovieSessionResponseDto map(MovieSession movieSession) {
        return MovieSessionResponseDto.newBuilder()
               .setCinemaHallCapacity(movieSession.getCinemaHall().getCapacity())
               .setCinemaHallId(movieSession.getCinemaHall().getId())
               .setMovieTitle(movieSession.getMovie().getTitle())
               .setShowTime(movieSession.getShowTime().format(FORMATTER))
               .build();
    }
}
