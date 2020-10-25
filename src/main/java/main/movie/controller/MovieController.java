package main.movie.controller;

import java.util.List;
import java.util.stream.Collectors;
import main.movie.model.dto.MovieResponseDto;
import main.movie.model.dto.mapper.MovieResponseMapper;
import main.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;
    private final MovieResponseMapper responseMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieResponseMapper responseMapper) {
        this.movieService = movieService;
        this.responseMapper = responseMapper;
    }

    @PostMapping("/new")
    public void addMovie(@RequestBody MovieResponseDto movieDto) {
        movieService.add(responseMapper.map(movieDto));
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(responseMapper::map)
                .collect(Collectors.toList());
    }
}
