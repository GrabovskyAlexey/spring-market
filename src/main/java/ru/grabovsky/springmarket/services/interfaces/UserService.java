package ru.grabovsky.springmarket.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.grabovsky.springmarket.entity.User;

/**
 * Интерфейс UserService расширяющий UserDetailsService для работы Spring Security
 *
 * @author grabovsky.alexey
 */
public interface UserService extends UserDetailsService {
    /**
     * Генерирует JWT токен на основании имени пользователя
     *
     * @param username Имя пользователя для генерации токена
     * @return Сгенерированный JWT токен для указанного имени пользователя
     */
    String getTokenByUsername(String username);

    /**
     * Проверка существования пользователя с указанным именем, без учета регистра символов
     *
     * @param username Имя пользователя для проверки
     * @return Существует пользователь или нет
     */
    boolean existsUserByUsernameIgnoreCase(String username);

    /**
     * Проверка занятости адреса электронной почты, без учета регистра символов
     *
     * @param email Адрес электронной почты
     * @return Занят адрес электронной почты или нет
     */
    boolean existsUserByEmailIgnoreCase(String email);

    /**
     * Поиск пользователя по адресу электронной почты
     *
     * @param email Адрес электронной почты для поиска
     * @return Пользователь
     */
    User findByEmail(String email);

    /**
     * Сохраняет пользователя в БД
     *
     * @param user Пользователь для сохранения
     */
    void save(User user);
}
