package main.movie.model.dto.mapper;

import java.util.stream.Collectors;
import main.movie.model.Order;
import main.movie.model.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketsMapper;

    @Autowired
    public OrderMapper(TicketMapper ticketsMapper) {
        this.ticketsMapper = ticketsMapper;
    }

    public OrderResponseDto map(Order order) {
        return new OrderResponseDto(order.getTickets().stream()
                .map(ticketsMapper::map)
                .collect(Collectors.toList()),
                order.getOrderDate(),
                order.getUser().getId());
    }
}
