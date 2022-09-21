package ru.grabovsky.springmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grabovsky.springmarket.entity.auth.Role;
import ru.grabovsky.springmarket.repositories.RoleRepository;
import ru.grabovsky.springmarket.services.interfaces.RoleService;

import java.util.Optional;

/**
 * Имплементация RoleService
 *
 * @author grabovsky.alexey
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    /**
     * Поиск роли по названию
     *
     * @param role Название роли для поиска
     * @return Optional найденной роли
     */
    @Override
    public Optional<Role> findByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
