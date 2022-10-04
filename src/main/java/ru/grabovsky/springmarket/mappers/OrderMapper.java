package ru.grabovsky.springmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.grabovsky.market.api.dto.order.OrderDto;
import ru.grabovsky.springmarket.entity.order.Order;

@Mapper(uses = {OrderItemMapping.class, DeliveryAddressMapper.class})
public interface OrderMapper {
    @Mappings(value = {
            @Mapping(target = "userId", source = "user.id"),
    })
    OrderDto mapToDto(Order order);

    Order mapFromDto(OrderDto dto);
}
