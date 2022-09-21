package ru.grabovsky.springmarket.services.interfaces;

import ru.grabovsky.springmarket.entity.auth.Profile;

/**
 * Интерфейс ProfileService
 *
 * @author grabovsky.alexey
 */
public interface ProfileService {
    /**
     * Сохранение профиля пользователя
     *
     * @param profile Профиль для сохранения
     */
    void save(Profile profile);
}
