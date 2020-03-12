package ua.com.jarvis.domain.dto.input;

import lombok.Data;

@Data
public class CreateUserInputDto {

    private String username;
    private String email;
    private String password;

    public CreateUserInputDto() {
    }

    public CreateUserInputDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
