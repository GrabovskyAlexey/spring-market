package ru.grabovsky.springmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.grabovsky.market.api.dto.register.RegisterRequestDto;
import ru.grabovsky.springmarket.entity.auth.Profile;
import ru.grabovsky.springmarket.entity.auth.Role;
import ru.grabovsky.springmarket.entity.auth.User;
import ru.grabovsky.springmarket.exceptions.user.RoleNotFoundException;
import ru.grabovsky.springmarket.exceptions.user.UserAlreadyExistsException;
import ru.grabovsky.springmarket.services.interfaces.ProfileService;
import ru.grabovsky.springmarket.services.interfaces.RegisterService;
import ru.grabovsky.springmarket.services.interfaces.RoleService;
import ru.grabovsky.springmarket.services.interfaces.UserService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Имплементация RegisterService
 *
 * @author grabovsky.alexey
 */

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    private final ProfileService profileService;

    /**
     * Регистрация пользователя
     *
     * @param request Запрос на регистрацию
     * @return Сущность зарегистрированного пользователя
     */
    @Override
    public User register(RegisterRequestDto request) {
        checkExistsUsernameOrEmail(request);
        return createUser(request);
    }

    /**
     * Проверка на существование пользователя с таким же именем или адресом электронной почты
     *
     * @param request Запрос на регистрацию
     * @throws UserAlreadyExistsException
     *
     */
    private void checkExistsUsernameOrEmail(RegisterRequestDto request) {
        boolean existsUsername = userService.existsUserByUsernameIgnoreCase(request.getUsername());
        boolean existsEmail = userService.existsUserByEmailIgnoreCase(request.getEmail());
        if (existsUsername && existsEmail) {
            throw new UserAlreadyExistsException(String.format(
                    "Имя пользователя '%s' и электронная почта '%s' уже используются", request.getUsername(), request.getEmail()));
        }
        if (existsUsername) {
            throw new UserAlreadyExistsException(String.format(
                    "Имя пользователя '%s' уже используются", request.getUsername()));
        }
        if (existsEmail) {
            throw new UserAlreadyExistsException(String.format(
                    "Электронная почта '%s' уже используются", request.getEmail()));
        }
    }

    /**
     * Создание и сохранение пользователя
     *
     * @param request Запрос на регистрацию
     * @return Сохраненного в БД пользователя
     */

    private User createUser(RegisterRequestDto request) {
        Set<Role> roles = new HashSet<>();
        roles.add(getDefaultRole());
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .activationCode(UUID.randomUUID().toString())
                .roles(roles)
                .enabled(true)
                .activated(true)
                .build();
        userService.save(user);
        createProfile(user);
        return user;
    }

    /**
     * Создание профиля пользователя
     *
     * @param user Пользователь
     */
    private void createProfile(User user){
        Profile profile = Profile.builder()
                .regDate(LocalDate.now(ZoneId.of("UTC")))
                .name(user.getUsername())
                .build();
        profile.setUser(user);
        profileService.save(profile);
    }

    /**
     * Получение базовой роли ROLE_USER
     *
     * @return Роль
     */
    private Role getDefaultRole() {
        return roleService.findByRole("ROLE_USER")
                .orElseThrow(() -> new RoleNotFoundException("ROLE_USER not found"));
    }
}
