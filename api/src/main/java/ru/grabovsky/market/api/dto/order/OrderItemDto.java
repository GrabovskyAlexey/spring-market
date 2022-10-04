package ru.grabovsky.market.api.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * OrderItemDto
 *
 * @author grabovsky.alexey
 * @created 22.09.2022 15:03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Order item information", name = "OrderItem")
public class OrderItemDto {
    @Schema(description = "Order item id")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Item quantity")
    @JsonProperty("quantity")
    private int quantity;

    @Schema(description = "Item price per product")
    @JsonProperty("pricePerProduct")
    private BigDecimal pricePerProduct;

    @Schema(description = "Total item price")
    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

    @Schema(description = "Product id")
    @JsonProperty("productId")
    private Long productId;
}
