package main.movie.model.dto.mapper;

import main.movie.model.CinemaHall;
import main.movie.model.dto.CinemaHallRequestDto;
import main.movie.model.dto.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {

    public CinemaHall map(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall hall = new CinemaHall();
        hall.setCapacity(cinemaHallRequestDto.getCapacity());
        hall.setDescription(cinemaHallRequestDto.getDescription());
        return hall;
    }

    public CinemaHallResponseDto map(CinemaHall hall) {
        return new CinemaHallResponseDto(hall.getId(), hall.getCapacity(), hall.getDescription());
    }
}
