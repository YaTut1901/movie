package main.movie.model.dto;

public class MovieSessionResponseDto {
    private String showTime;
    private int cinemaHallCapacity;
    private Long cinemaHallId;
    private String movieTitle;

    public MovieSessionResponseDto() {
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public int getCinemaHallCapacity() {
        return cinemaHallCapacity;
    }

    public void setCinemaHallCapacity(int cinemaHallCapacity) {
        this.cinemaHallCapacity = cinemaHallCapacity;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public static Builder newBuilder() {
        return new MovieSessionResponseDto().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder setShowTime(String showTime) {
            MovieSessionResponseDto.this.showTime = showTime;
            return this;
        }

        public Builder setCinemaHallCapacity(int cinemaHallCapacity) {
            MovieSessionResponseDto.this.cinemaHallCapacity = cinemaHallCapacity;
            return this;
        }

        public Builder setCinemaHallId(Long cinemaHallId) {
            MovieSessionResponseDto.this.cinemaHallId = cinemaHallId;
            return this;
        }

        public Builder setMovieTitle(String movieTitle) {
            MovieSessionResponseDto.this.movieTitle = movieTitle;
            return this;
        }

        public MovieSessionResponseDto build() {
            return MovieSessionResponseDto.this;
        }
    }
}
