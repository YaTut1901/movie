package main.movie.model.dto;

public class CinemaHallResponseDto {
    private Long cinemaHallId;
    private int capacity;
    private String description;

    public CinemaHallResponseDto(Long cinemaHallId, int capacity, String description) {
        this.cinemaHallId = cinemaHallId;
        this.capacity = capacity;
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }
}
