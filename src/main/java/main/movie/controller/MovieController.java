package main.movie.controller;

import java.util.List;
import java.util.stream.Collectors;
import main.movie.model.dto.MovieRequestDto;
import main.movie.model.dto.mapper.MovieMapper;
import main.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper responseMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper responseMapper) {
        this.movieService = movieService;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    public void addMovie(@RequestBody MovieRequestDto movieDto) {
        movieService.add(responseMapper.map(movieDto));
    }

    @GetMapping
    public List<MovieRequestDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(responseMapper::map)
                .collect(Collectors.toList());
    }
}
