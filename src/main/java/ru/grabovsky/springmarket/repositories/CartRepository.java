package ru.grabovsky.springmarket.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.grabovsky.market.api.dto.CartDto;

import java.util.ArrayList;

/**
 * Репозиторий для работы с корзиной в Redis
 *
 * @author grabovsky.alexey
 * @created 18.09.2022 16:22
 */
@Repository
@RequiredArgsConstructor
public class CartRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public CartDto getCartById(String id) {
        String cacheId = "userCart::" + id;
        if (Boolean.FALSE.equals(redisTemplate.hasKey(cacheId))) {
            CartDto cartDto = CartDto.builder()
                    .id(id)
                    .cartItems(new ArrayList<>())
                    .build();
            redisTemplate.opsForValue().set(cacheId, cartDto);
        }
        return (CartDto) redisTemplate.opsForValue().get(cacheId);
    }
}