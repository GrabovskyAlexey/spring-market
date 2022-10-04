package ru.grabovsky.springmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.market.api.dto.order.DeliveryAddressDto;
import ru.grabovsky.market.api.dto.util.MessageDto;
import ru.grabovsky.springmarket.controllers.interfaces.AddressController;
import ru.grabovsky.springmarket.entity.order.DeliveryAddress;
import ru.grabovsky.springmarket.mappers.DeliveryAddressMapper;
import ru.grabovsky.springmarket.services.interfaces.AddressService;

import java.security.Principal;
import java.util.List;

/**
 * AddressControllerImpl
 *
 * @author grabovsky.alexey
 * @created 23.09.2022 16:27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/${market.api.url}/addresses")
public class AddressControllerImpl implements AddressController {
    private final AddressService addressService;
    private final DeliveryAddressMapper addressMapper;

    @Override
    public ResponseEntity<List<DeliveryAddressDto>> getAllAddressesByUsername(Principal principal) {
        return ResponseEntity.ok(addressService.getAllAddressesByUsername(principal.getName()));
    }

    @Override
    public ResponseEntity<DeliveryAddressDto> getaAddressById(Principal principal, Long id) {
        return ResponseEntity.ok(
                addressMapper.mapToDto(
                        addressService.getAddressById(principal.getName(), id)
                )
        );
    }

    @Override
    public ResponseEntity<MessageDto> createAddress(Principal principal, DeliveryAddressDto dto) {
        DeliveryAddress address = addressService.createAddress(principal.getName(), dto);
        return ResponseEntity.ok(new MessageDto(
                String.format("Адрес создан. Идентификатор %s", address.getId())
        ));
    }

    @Override
    public ResponseEntity<MessageDto> updateAddressById(Principal principal, Long id, DeliveryAddressDto dto) {
        DeliveryAddress address = addressService.updateAddressById(principal.getName(), id, dto);
        return ResponseEntity.ok(new MessageDto(
                String.format("Адрес с идентификатором %s обновлен", address.getId())
        ));
    }

    @Override
    public ResponseEntity<MessageDto> deleteAddressById(Long id) {
        addressService.deleteAddressById(id);
        return ResponseEntity.ok(new MessageDto(
                String.format("Адрес с идентификатором %s удален", id)
        ));
    }
}
