package ru.grabovsky.market.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * DTO для описания категории
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 11:49
 */
@Data
@Schema(description = "Category information", name = "Category")
public class CategoryDto {
    @Schema(description = "Category id", example = "null")
    @Min(value = 1)
    private Long id;

    @NotEmpty(message = "Название категории не может быть пустым")
    @Size(min = 3, max = 50, message = "Название категории должно содержать от 3  до 50 символов")
    @Schema(description = "Category name")
    @JsonProperty("categoryName")
    private String categoryName;
}
