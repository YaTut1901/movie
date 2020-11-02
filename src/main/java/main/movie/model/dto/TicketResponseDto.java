package main.movie.model.dto;

public class TicketResponseDto {
    private MovieSessionResponseDto movieSessionResponseDto;
    private Long userId;

    public TicketResponseDto(MovieSessionResponseDto movieSessionResponseDto,
                             Long userId) {
        this.movieSessionResponseDto = movieSessionResponseDto;
        this.userId = userId;
    }

    public TicketResponseDto() {
    }

    public MovieSessionResponseDto getMovieSessionResponseDto() {
        return movieSessionResponseDto;
    }

    public void setMovieSessionResponseDto(MovieSessionResponseDto movieSessionResponseDto) {
        this.movieSessionResponseDto = movieSessionResponseDto;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
