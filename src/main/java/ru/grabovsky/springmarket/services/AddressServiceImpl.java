package ru.grabovsky.springmarket.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.grabovsky.market.api.dto.order.DeliveryAddressDto;
import ru.grabovsky.springmarket.entity.auth.User;
import ru.grabovsky.springmarket.entity.order.DeliveryAddress;
import ru.grabovsky.springmarket.exceptions.IllegalJsonFieldValueException;
import ru.grabovsky.springmarket.exceptions.orders.DeliveryAddressAccessDeniedException;
import ru.grabovsky.springmarket.exceptions.orders.DeliveryAddressNotFoundException;
import ru.grabovsky.springmarket.mappers.DeliveryAddressMapper;
import ru.grabovsky.springmarket.repositories.AddressRepository;
import ru.grabovsky.springmarket.services.interfaces.AddressService;
import ru.grabovsky.springmarket.services.interfaces.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AddressServiceImpl
 *
 * @author grabovsky.alexey
 * @created 23.09.2022 11:55
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final DeliveryAddressMapper addressMapper;
    private final UserService userService;

    @Override
    public DeliveryAddress getAddressById(String username, Long id) {
        User user = userService.findByUsername(username);

        DeliveryAddress address = addressRepository.findById(id).orElseThrow(
                () -> new DeliveryAddressNotFoundException(
                        String.format("Адрес с идентификатором %s не найден", id)
                )
        );
        checkAddressByUser(user, address);
        return address;
    }

    @Override
    public List<DeliveryAddressDto> getAllAddressesByUsername(String username) {
        User user = userService.findByUsername(username);
        return addressRepository.findAllByUser(user).stream()
                .map(addressMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryAddress createAddress(String username, DeliveryAddressDto dto) {
        User user = userService.findByUsername(username);
        DeliveryAddress address = addressMapper.mapFromDto(dto);
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public DeliveryAddress updateAddressById(String username, Long id, DeliveryAddressDto dto) {
        if (dto.getId() == null) {
            throw new IllegalJsonFieldValueException("id", "При обновлении адреса идентификатор не может быть пустым");
        }
        if (!id.equals(dto.getId())) {
            throw new IllegalJsonFieldValueException("id", "Идентификатор в теле запроса не совпадает с идентификатором в адресе запроса");
        }
        User user = userService.findByUsername(username);
        DeliveryAddress address = addressRepository.findById(id).orElseThrow(() ->
                new DeliveryAddressNotFoundException(String.format("Адрес с идентификатором %s не найден", id))
        );
        log.info("user from db: {} user from address: {}", user, address.getUser());
        checkAddressByUser(user, address);
        address = addressMapper.mapFromDto(dto);
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddressById(Long id) {
        DeliveryAddress address = addressRepository.findById(id).orElseThrow(() ->
                new DeliveryAddressNotFoundException(String.format("Адрес с идентификатором %s не найден", id))
        );
        addressRepository.delete(address);
    }

    private void checkAddressByUser(User user, DeliveryAddress address){
        if(!address.getUser().equals(user)){
            throw new DeliveryAddressAccessDeniedException("У вас нет доступа к данному адресу доставки");
        }
    }
}
