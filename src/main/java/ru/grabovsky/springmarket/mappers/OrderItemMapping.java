package ru.grabovsky.springmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.grabovsky.market.api.dto.order.OrderItemDto;
import ru.grabovsky.springmarket.entity.order.OrderItem;

@Mapper
public interface OrderItemMapping {
    @Mappings(
            @Mapping(target = "productId", source = "product.id")
    )
    OrderItemDto mapToDto(OrderItem orderItem);
}
