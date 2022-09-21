package ru.grabovsky.springmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grabovsky.springmarket.entity.product.Product;

/**
 * ProductRepository
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 15:22
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
