package ru.grabovsky.market.api.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Authenticate response", name = "AuthResponse")
public class AuthResponseDto {

    @Schema(description = "JWT access token")
    @JsonProperty("token")
    private String token;
}
