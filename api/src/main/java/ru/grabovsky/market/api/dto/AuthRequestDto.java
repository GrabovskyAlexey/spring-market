package ru.grabovsky.market.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Authenticate request", name = "AuthRequest")
public class AuthRequestDto {
    @Schema(description = "Username", example = "user")
    @JsonProperty("username")
    private String username;

    @Schema(description = "User password", example = "pa$$w0rd")
    @JsonProperty("password")
    private String password;
}
