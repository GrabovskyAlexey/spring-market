package ru.grabovsky.springmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grabovsky.springmarket.entity.auth.User;
import ru.grabovsky.springmarket.entity.order.DeliveryAddress;

import java.util.List;

/**
 * AddressRepository
 *
 * @author grabovsky.alexey
 * @created 23.09.2022 13:39
 */
public interface AddressRepository extends JpaRepository<DeliveryAddress, Long> {

    List<DeliveryAddress> findAllByUser(User user);
}
