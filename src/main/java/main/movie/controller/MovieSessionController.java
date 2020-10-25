package main.movie.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import main.movie.model.dto.MovieSessionRequestDto;
import main.movie.model.dto.MovieSessionResponseDto;
import main.movie.model.dto.mapper.MovieSessionRequestMapper;
import main.movie.model.dto.mapper.MovieSessionResponseMapper;
import main.movie.service.MovieSessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final MovieSessionService movieSessionService;
    private final MovieSessionResponseMapper responseMapper;
    private final MovieSessionRequestMapper requestMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionResponseMapper responseMapper,
                                  MovieSessionRequestMapper requestMapper) {
        this.movieSessionService = movieSessionService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PostMapping("/new")
    public void addSession(@RequestBody MovieSessionRequestDto movieSessionDto) {
        movieSessionService.add(requestMapper.map(movieSessionDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailable(@RequestParam Long movieId,
                                                      @RequestParam String date) {
        return movieSessionService.findAvailableSessions(movieId,
                LocalDate.parse(date, FORMATTER)).stream()
                .map(responseMapper::map)
                .collect(Collectors.toList());
    }
}
