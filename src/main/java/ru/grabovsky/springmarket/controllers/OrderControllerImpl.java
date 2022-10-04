package ru.grabovsky.springmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.market.api.dto.cart.CartDto;
import ru.grabovsky.market.api.dto.order.OrderDto;
import ru.grabovsky.market.api.dto.util.MessageDto;
import ru.grabovsky.market.api.dto.util.PageDto;
import ru.grabovsky.springmarket.controllers.interfaces.OrderController;
import ru.grabovsky.springmarket.services.interfaces.OrderService;

import java.security.Principal;

/**
 * Имплементация OrderController
 *
 * @author grabovsky.alexey
 * @created 21.09.2022 19:47
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/${market.api.url}/orders")
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;

    @Override
    public ResponseEntity<PageDto<OrderDto>> getAllOrdersByUsername(
//            String username,
            Principal principal,
            Integer p, Integer limit) {
        return ResponseEntity.ok(orderService.getAllOrdersByUsername(principal.getName(), p, limit));
    }

    @Override
    public ResponseEntity<OrderDto> getOrderById(
//            String username,
            Principal principal,
            Long id) {
        return ResponseEntity.ok(orderService.getOrderById(principal.getName(), id));
    }

    @Override
    public ResponseEntity<MessageDto> createOrder(
//            String username,
            Principal principal,
            OrderDto dto) {
        OrderDto order = orderService.createOrder(principal.getName(), dto);
        return ResponseEntity.ok(
                new MessageDto(
                        String.format("Создан заказ с номером %s", order.getId())
                )
        );
    }

    @Override
    public ResponseEntity<MessageDto> updateOrderById(Long id, OrderDto dto) {
        OrderDto order = orderService.updateOrderById(id, dto);
        return ResponseEntity.ok(
                new MessageDto(
                        String.format("Заказ c номером %s обновлен", order.getId())
                )
        );
    }

    @Override
    public ResponseEntity<MessageDto> deleteOrderById(Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok(
                new MessageDto(
                        String.format("Заказ c номером %s удален", id)
                )
        );
    }
}
