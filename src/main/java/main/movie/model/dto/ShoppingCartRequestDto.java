package main.movie.model.dto;

public class ShoppingCartRequestDto {
    private Long userId;

    public ShoppingCartRequestDto(Long userId) {
        this.userId = userId;
    }

    public ShoppingCartRequestDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
