package ru.grabovsky.springmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grabovsky.springmarket.entity.Profile;
import ru.grabovsky.springmarket.repositories.ProfileRepository;
import ru.grabovsky.springmarket.services.interfaces.ProfileService;

/**
 * Имплементация ProfileService
 *
 * @author grabovsky.alexey
 */

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    /**
     * Реализация сохранения профиля
     *
     * @param profile Профиль для сохранения
     */
    @Override
    public void save(Profile profile) {
        profileRepository.save(profile);
    }
}
