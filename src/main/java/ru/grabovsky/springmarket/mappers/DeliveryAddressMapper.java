package ru.grabovsky.springmarket.mappers;

import org.mapstruct.Mapper;
import ru.grabovsky.market.api.dto.order.DeliveryAddressDto;
import ru.grabovsky.springmarket.entity.order.DeliveryAddress;

/**
 * DeliveryAddressMapper
 *
 * @author grabovsky.alexey
 * @created 23.09.2022 12:54
 */
@Mapper
public interface DeliveryAddressMapper {
    DeliveryAddress mapFromDto(DeliveryAddressDto dto);
    DeliveryAddressDto mapToDto(DeliveryAddress address);
}
