package ru.grabovsky.market.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Validation error info", name = "ValidationError")
public class ValidationError {
    @Schema(description = "Имя поля в котором возникла ошибка", example ="username")
    @JsonProperty("fieldName")
    private String fieldName;
    @Schema(description = "Сообщение об ошибке", example ="Username must not be null")
    @JsonProperty("fieldName")
    private String message;
}