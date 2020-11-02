package main.movie.model.dto.mapper;

import main.movie.model.Ticket;
import main.movie.model.dto.TicketResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    private MovieSessionMapper sessionMapper;

    @Autowired
    public TicketMapper(MovieSessionMapper sessionMapper) {
        this.sessionMapper = sessionMapper;
    }

    public TicketResponseDto map(Ticket ticket) {
        return new TicketResponseDto(sessionMapper.map(ticket.getMovieSession()),
                ticket.getUser().getId());
    }
}
