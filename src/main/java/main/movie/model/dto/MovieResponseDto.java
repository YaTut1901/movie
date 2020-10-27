package main.movie.model.dto;

public class MovieResponseDto {
    private Long movieId;
    private String title;
    private String description;

    public MovieResponseDto(Long movieId, String title, String description) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
