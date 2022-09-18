package ru.grabovsky.market.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.grabovsky.market.api.dto.interfaces.PageableDto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO для описания продукта
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 12:54
 */
@Data
@Builder
@Schema(description = "Product information", name = "Product")
public class ProductDto implements PageableDto {
    @Schema(description = "Product id")
    @Min(value = 1)
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Product name")
    @NotEmpty(message = "Название продукта не может быть пустым")
    @Size(min = 3, max = 50, message = "Название продукта должно содержать от 3  до 50 символов")
    @JsonProperty("title")
    private String title;

    @Schema(description = "Product description")
    @Size(min = 5, message = "Описание продукта должно содержать от минимум 5 символов")
    @JsonProperty("description")
    private String description;

    @Schema(description = "Product price", example = "10.07")
    @NotEmpty(message = "Цена продукта не может быть пустой")
    @Positive
    @JsonProperty("price")
    private BigDecimal price;

    @Schema(description = "Product rating")
    @Min(0)
    @Max(5)
    @JsonProperty("rating")
    private BigDecimal rating;

    @Schema(description = "Product category")
    @NotEmpty(message = "Категория продукта не может быть пустой")
    @JsonProperty("categoryName")
    private String categoryName;

    @Schema(description = "Product images")
    @JsonProperty("productImages")
    private List<ProductImageDto> productImages;

    @Schema(description = "Product remaining on stock")
    @JsonProperty("remaining")
    private Integer remaining;
}
