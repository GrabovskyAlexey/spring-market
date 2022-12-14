package ru.grabovsky.market.api.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * CartDto
 *
 * @author grabovsky.alexey
 * @created 18.09.2022 16:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Cart information", name = "Cart")
public class CartDto {
    @Schema(description = "Cart id")
    @JsonProperty("id")
    private String id;

    @Schema(description = "Items in cart")
    @JsonProperty("cartItems")
    private List<CartItemDto> cartItems;

    @Schema(description = "Cart total sum")
    @JsonProperty("totalSum")
    private BigDecimal totalSum;


    /**
     * Пересчет общей суммы корзины
     *
     */
    public void recalculate(){
        deleteEmptyCartItems();
        totalSum = cartItems.stream()
                .map(CartItemDto::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Очистка корзины
     *
     */
    public void clear(){
        cartItems.clear();
        totalSum = BigDecimal.ZERO;
    }

    /**
     * Добавление товара в корзину
     *
     * @param itemDto Товар для добавления
     */
    public void addItem(CartItemDto itemDto){
        for(CartItemDto item : cartItems){
            if(item.getProductId().equals(itemDto.getProductId())){
                item.changeQuantity(itemDto.getQuantity());
                item.setPricePerProduct(itemDto.getPricePerProduct());
                recalculate();
                return;
            }
        }
        cartItems.add(itemDto);
        recalculate();
    }

    /**
     * Удаление товара из корзины
     *
     * @param itemId Идентифкатор товара для удаления
     */
    public void removeItem(Long itemId){
        if(cartItems.removeIf(i -> i.getProductId().equals(itemId))) {
            recalculate();
        }
    }

    /**
     * Очистка корзины от товаров с количеством 0 или меньше
     *
     */
    private void deleteEmptyCartItems(){
        cartItems.removeIf(item -> item.getQuantity() <= 0);
    }
}
