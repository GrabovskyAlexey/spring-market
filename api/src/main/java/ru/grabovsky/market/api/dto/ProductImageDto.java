package ru.grabovsky.market.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * DTO для описания изображений товара
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 17:21
 */
@Data
@Schema(description = "Product image", name = "Product")
public class ProductImageDto {
    @Schema(description = "Image id")
    @Min(value = 1)
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Image url")
    @NotEmpty(message = "Ссылка на изображение не может быть пустой")
    @JsonProperty("imageUrl")
    private String imageUrl;
}
