package ru.grabovsky.springmarket.services.interfaces;

import ru.grabovsky.market.api.dto.register.RegisterRequestDto;
import ru.grabovsky.springmarket.entity.auth.User;

/**
 * Интерфейс RegisterService
 *
 * @author grabovsky.alexey
 */
public interface RegisterService {
    /**
     * Регистрация пользователя
     *
     * @param request Запрос на регистрацию
     * @return Зарегистрированный пользователь
     */
    User register(RegisterRequestDto request);
}
