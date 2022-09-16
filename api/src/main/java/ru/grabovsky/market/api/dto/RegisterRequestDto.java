package ru.grabovsky.market.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Registration request", name = "RegisterRequest")
public class RegisterRequestDto {
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    @Size(min = 3, max = 20, message = "Имя пользователя должно содержать от 3  до 20 символов")
    @Schema(description = "Имя пользователя", example ="user")
    @JsonProperty("username")
    private String username;

    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 8, message = "Пароль должен быть не меньше 8 симовлов")
    @Schema(description = "Пароль", example ="pa$$w0rd")
    @JsonProperty("password")
    private String password;

    @NotEmpty(message = "Адрес электронной почты не может быть пустым")
    @Email(message = "Введите корректный адрес электронной почты")
    @Schema(description = "Адрес электронной почты", example ="user@example.com")
    @JsonProperty("email")
    private String email;
}

