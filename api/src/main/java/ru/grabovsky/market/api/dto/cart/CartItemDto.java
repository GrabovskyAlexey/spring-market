package ru.grabovsky.market.api.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * CartItemDto
 *
 * @author grabovsky.alexey
 * @created 19.09.2022 09:04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Cart item information", name = "CartItem")
public class CartItemDto {
    @Schema(description = "Product id")
    @JsonProperty("productId")
    private Long productId;

    @Schema(description = "Product name")
    @JsonProperty("productName")
    private String productName;

    @Schema(description = "Quantity")
    @JsonProperty("quantity")
    private Integer quantity;

    @Schema(description = "Price per product")
    @JsonProperty("pricePerProduct")
    private BigDecimal pricePerProduct;

    @Schema(description = "Total price")
    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

    /**
     * Изменение количества товара и пересчет отбей стоимости
     *
     * @param delta Значение на которое изменить количество
     */
    public void changeQuantity(int delta){
        quantity += delta;
        if(quantity < 0){
            quantity = 0;
        }
        totalPrice = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }
}
