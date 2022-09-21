package ru.grabovsky.springmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.grabovsky.market.api.dto.cart.CartDto;
import ru.grabovsky.market.api.dto.cart.CartItemDto;
import ru.grabovsky.springmarket.repositories.CartRepository;
import ru.grabovsky.springmarket.services.interfaces.CartService;

/**
 * Имплементация CartService
 *
 * @author grabovsky.alexey
 * @created 19.09.2022 21:31
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository repository;
    // TODO: реализовать обновление цены товара при изменениях в корзине с запросом из БД/Кеша
    /**
     * Получить корзину по id
     *
     * @param cartId Идентификатор корзины
     * @return DTO корзины
     */
    @Override
    @Cacheable(value = "userCart", key = "#cartId")
    public CartDto getCart(String cartId) {
        return repository.getCartById(cartId);
    }

    /**
     * Добавить товар в корзину
     *
     * @param cartId Идентификатор корзины
     * @param itemDto Товар для добавления в корзину
     * @return DTO корзины
     */
    @Override
    @CachePut(value = "userCart", key = "#cartId")
    public CartDto addProductToCart(String cartId, CartItemDto itemDto) {
        CartDto cart = getCart(cartId);
        cart.addItem(itemDto);
        return cart;
    }

    /**
     * Удалить товар из корзины
     *
     * @param cartId Идентификатор корзины
     * @param itemId Идентификатор товара для удаления
     * @return DTO корзины
     */
    @Override
    @CachePut(value = "userCart", key = "#cartId")
    public CartDto deleteProductFromCartById(String cartId, Long itemId) {
        CartDto cart = getCart(cartId);
        cart.removeItem(itemId);
        return cart;
    }

    /**
     * Изменить количество товара в корзине
     *
     * @param cartId Идентификатор корзины
     * @param itemId Идентификатор товара для изменения количества
     * @param delta На сколько изменить количество
     * @return DTO корзины
     */
    @Override
    @CachePut(value = "userCart", key = "#cartId")
    public CartDto changeQuantity(String cartId, Long itemId, int delta) {
        CartDto cart = getCart(cartId);
        for (CartItemDto item : cart.getCartItems()){
            if(item.getProductId().equals(itemId)){
                item.changeQuantity(delta);
                cart.recalculate();
                break;
            }
        }
        return cart;
    }

    /**
     * Очистить корзину
     *
     * @param cartId Идентификатор корзины
     */
    @Override
    @CacheEvict(value = "userCart", key = "#cartId")
    public void clear(String cartId) {
        CartDto cart = getCart(cartId);
        cart.clear();
    }
}
