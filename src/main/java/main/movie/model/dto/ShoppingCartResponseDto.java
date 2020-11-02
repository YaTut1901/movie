package main.movie.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long cartId;
    private List<TicketResponseDto> tikets;
    private Long userId;

    public ShoppingCartResponseDto(Long cartId,
                                   List<TicketResponseDto> tikets,
                                   Long userId) {
        this.cartId = cartId;
        this.tikets = tikets;
        this.userId = userId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<TicketResponseDto> getTikets() {
        return tikets;
    }

    public void setTikets(List<TicketResponseDto> tikets) {
        this.tikets = tikets;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
