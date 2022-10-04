package ru.grabovsky.springmarket.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.grabovsky.market.api.dto.order.DeliveryAddressDto;
import ru.grabovsky.market.api.dto.order.OrderDto;
import ru.grabovsky.market.api.dto.order.OrderStatus;
import ru.grabovsky.market.api.dto.util.PageDto;
import ru.grabovsky.springmarket.entity.auth.User;
import ru.grabovsky.springmarket.entity.order.DeliveryAddress;
import ru.grabovsky.springmarket.entity.order.Order;
import ru.grabovsky.springmarket.entity.order.OrderItem;
import ru.grabovsky.springmarket.exceptions.IllegalJsonFieldValueException;
import ru.grabovsky.springmarket.exceptions.orders.DeliveryAddressAccessDeniedException;
import ru.grabovsky.springmarket.exceptions.orders.OrderAccessDeniedException;
import ru.grabovsky.springmarket.exceptions.orders.OrderNotFoundException;
import ru.grabovsky.springmarket.mappers.OrderMapper;
import ru.grabovsky.springmarket.mappers.ProductMapper;
import ru.grabovsky.springmarket.repositories.OrderRepository;
import ru.grabovsky.springmarket.services.interfaces.AddressService;
import ru.grabovsky.springmarket.services.interfaces.OrderService;
import ru.grabovsky.springmarket.services.interfaces.ProductService;
import ru.grabovsky.springmarket.services.interfaces.UserService;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

/**
 * Имплементация OrderService
 *
 * @author grabovsky.alexey
 * @created 23.09.2022 10:39
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AddressService addressService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;

    @Override
    public PageDto<OrderDto> getAllOrdersByUsername(String username, Integer p, Integer limit) {
        Pageable page = PageRequest.of(p - 1, limit);
        User user = userService.findByUsername(username);
        Page<Order> orders = orderRepository.findOrdersByUser(user, page);
        return PageDto.<OrderDto>builder()
                .first(orders.isFirst())
                .last(orders.isLast())
                .pageIndex(orders.getNumber())
                .pageSize(orders.getSize())
                .totalPages(orders.getTotalPages())
                .totalElements(orders.getTotalElements())
                .pageItems(
                        orders.stream()
                                .map(orderMapper::mapToDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public OrderDto getOrderById(String username, Long id) {
        User user = userService.findByUsername(username);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("Заказ с номером %s не найден", id)
                ));
        if (!user.equals(order.getUser())) {
            throw new OrderAccessDeniedException("У вас нет доступа к данному заказу");
        }
        return orderMapper.mapToDto(order);
    }

    @Override
    @Transactional
    public OrderDto createOrder(String username, OrderDto dto) {
        Order order = new Order();
        User user = userService.findByUsername(username);
        order.setAddress(checkOrCreateDeliveryAddress(user, dto.getAddress()));
        order.setOrderStatus(dto.getStatus());
        order.setUser(user);
        order.setTotalSum(dto.getTotalSum());
        order.setOrderItems(
                dto.getItems().stream()
                        .map(item -> OrderItem.builder()
                                .order(order)
                                .pricePerProduct(item.getPricePerProduct())
                                .totalPrice(item.getTotalPrice())
                                .quantity(item.getQuantity())
                                .product(
                                        productMapper.mapFromDto(
                                                productService.getProductById(item.getProductId())
                                        )
                                )
                                .build())
                        .collect(Collectors.toSet()));
        return orderMapper.mapToDto(orderRepository.save(order));
    }

    private DeliveryAddress checkOrCreateDeliveryAddress(User user, DeliveryAddressDto dto) {
        DeliveryAddress address = null;
        if (dto.getId() != null) {
            address = addressService.getAddressById(user.getUsername(), dto.getId());
            if(!address.getUser().equals(user)){
                throw new DeliveryAddressAccessDeniedException("У вас нет доступа к данному адресу доставки");
            }
        } else {
            address = DeliveryAddress.builder()
                    .country(dto.getCountry())
                    .city(dto.getCity())
                    .region(dto.getRegion())
                    .street(dto.getStreet())
                    .house(dto.getHouse())
                    .flat(dto.getFlat())
                    .user(user)
                    .build();
        }
        return address;
    }

    @Override
    public OrderDto updateOrderById(Long id, OrderDto dto) {
        if (dto.getId() == null) {
            throw new IllegalJsonFieldValueException("id", "При обновлении заказа идентификатор не может быть пустым");
        }
        if (!id.equals(dto.getId())) {
            throw new IllegalJsonFieldValueException("id", "Идентификатор в теле запроса не совпадает с идентификатором в адресе запроса");
        }
        orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(String.format("Заказ с номером %s не найден", id))
        );
        Order order = orderMapper.mapFromDto(dto);
        orderRepository.save(order);
        return orderMapper.mapToDto(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(String.format("Заказ с номером %s не найден", id))
        );
        orderRepository.delete(order);
    }

    @Override
    public void changeOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException(String.format("Заказ с номером %s не найден", orderId))
        );
        order.setOrderStatus(status);
        orderRepository.save(order);
    }
}
