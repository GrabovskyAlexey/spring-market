package ru.grabovsky.springmarket.services.interfaces;

import org.springframework.http.ResponseEntity;
import ru.grabovsky.market.api.dto.cart.CartDto;
import ru.grabovsky.market.api.dto.order.OrderDto;
import ru.grabovsky.market.api.dto.order.OrderStatus;
import ru.grabovsky.market.api.dto.util.MessageDto;
import ru.grabovsky.market.api.dto.util.PageDto;

/**
 * Интерфейс OrderService
 *
 * @author grabovsky.alexey
 * @created 21.09.2022 19:04
 */
public interface OrderService {

    PageDto<OrderDto> getAllOrdersByUsername(String username, Integer p, Integer limit);

    OrderDto getOrderById(String username, Long id);

    OrderDto createOrder(String username, OrderDto dto);

    OrderDto updateOrderById(Long id, OrderDto order);

    void deleteOrderById(Long id);

    void changeOrderStatus(Long orderId, OrderStatus status);
}
