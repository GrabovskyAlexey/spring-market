package ru.grabovsky.springmarket.services.interfaces;

import ru.grabovsky.market.api.dto.CartDto;
import ru.grabovsky.market.api.dto.CartItemDto;

/**
 * Интерфейс CartService
 *
 * @author grabovsky.alexey
 * @created 18.09.2022 16:18
 */
public interface CartService {
    CartDto getCart(String cartId);

    CartDto addProductToCart(String cartId, CartItemDto item);

    CartDto deleteProductFromCartById(String cartId, Long itemId);

    CartDto changeQuantity(String cartId, Long itemId, int delta);

    void clear(String cartId);
}