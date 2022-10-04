package ru.grabovsky.springmarket.services.interfaces;

import ru.grabovsky.market.api.dto.order.DeliveryAddressDto;
import ru.grabovsky.market.api.dto.order.OrderDto;
import ru.grabovsky.springmarket.entity.auth.User;
import ru.grabovsky.springmarket.entity.order.DeliveryAddress;

import java.util.List;

/**
 * AddressService
 *
 * @author grabovsky.alexey
 * @created 23.09.2022 10:55
 */

public interface AddressService {
    DeliveryAddress getAddressById(String username, Long id);

    List<DeliveryAddressDto> getAllAddressesByUsername(String username);

    DeliveryAddress createAddress(String username, DeliveryAddressDto dto);

    DeliveryAddress updateAddressById(String username, Long id, DeliveryAddressDto dto);

    void deleteAddressById(Long id);
}
