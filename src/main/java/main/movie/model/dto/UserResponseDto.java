package main.movie.model.dto;

public class UserResponseDto {
    private String email;
    private String password;

    public UserResponseDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserResponseDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
