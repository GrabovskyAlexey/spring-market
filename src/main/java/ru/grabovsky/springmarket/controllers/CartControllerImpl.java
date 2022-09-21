package ru.grabovsky.springmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.market.api.dto.cart.CartDto;
import ru.grabovsky.market.api.dto.cart.CartItemDto;
import ru.grabovsky.springmarket.controllers.interfaces.CartController;
import ru.grabovsky.springmarket.services.interfaces.CartService;

import java.util.UUID;

/**
 * Имплементация CartController
 *
 * @author grabovsky.alexey
 * @created 19.09.2022 14:36
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/${market.api.url}/cart")
public class CartControllerImpl implements CartController {
    private final CartService cartService;
    @Override
    public CartDto getEmptyCart() {
        return cartService.getCart(UUID.randomUUID().toString());
    }

    @Override
    public CartDto getCart(String cartId) {
        return cartService.getCart(cartId);
    }

    @Override
    public CartDto addProductToCart(String cartId, CartItemDto cartItem) {
        return cartService.addProductToCart(cartId, cartItem);

    }

    @Override
    public CartDto deleteProductFromCart(String cartId, Long productId) {
        return cartService.deleteProductFromCartById(cartId, productId);
    }

    @Override
    public CartDto clearCart(String cartId) {
        cartService.clear(cartId);
        return cartService.getCart(cartId);
    }

    @Override
    public CartDto increaseProductQuantityInCart(String cartId, Long productId) {
        return cartService.changeQuantity(cartId, productId, 1);
    }

    @Override
    public CartDto decreaseProductQuantityInCart(String cartId, Long productId) {
        return cartService.changeQuantity(cartId, productId, -1);
    }
}
