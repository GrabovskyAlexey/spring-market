package ru.grabovsky.springmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grabovsky.springmarket.entity.category.Category;

import java.util.Optional;

/**
 * CategoryRepository
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 15:25
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
