package ru.grabovsky.springmarket.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grabovsky.springmarket.entity.auth.User;
import ru.grabovsky.springmarket.entity.order.Order;

import java.util.List;

/**
 * OrderRepository
 *
 * @author grabovsky.alexey
 * @created 21.09.2022 19:05
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findOrdersByUser(User user, Pageable pageable);
}
