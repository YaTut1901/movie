package main.movie.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private List<TicketResponseDto> tickets;
    private LocalDateTime orderDate;
    private Long userId;

    public OrderResponseDto(List<TicketResponseDto> tickets,
                            LocalDateTime orderDate,
                            Long userId) {
        this.tickets = tickets;
        this.orderDate = orderDate;
        this.userId = userId;
    }

    public OrderResponseDto() {
    }

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
