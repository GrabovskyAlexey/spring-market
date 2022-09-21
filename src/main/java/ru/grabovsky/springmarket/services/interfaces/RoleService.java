package ru.grabovsky.springmarket.services.interfaces;

import ru.grabovsky.springmarket.entity.auth.Role;

import java.util.Optional;

/**
 * Интерфейс RoleService
 *
 * @author grabovsky.alexey
 */
public interface RoleService {
    /**
     * Поиск роли по названию
     *
     * @param role Название роли для поиска
     * @return Пустой Optional или в найденной ролью
     */
    Optional<Role> findByRole(String role);
}
