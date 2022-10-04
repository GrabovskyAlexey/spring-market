package ru.grabovsky.springmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.grabovsky.springmarket.entity.auth.Authority;
import ru.grabovsky.springmarket.entity.auth.Role;
import ru.grabovsky.springmarket.entity.auth.User;
import ru.grabovsky.springmarket.exceptions.user.UserNotFoundException;
import ru.grabovsky.springmarket.repositories.UserRepository;
import ru.grabovsky.springmarket.services.interfaces.UserService;
import ru.grabovsky.springmarket.utils.JwtTokenUtil;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Имплементация UserService
 *
 * @author grabovsky.alexey
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    /**
     * Получение UserDetails по имени пользователя
     *
     * @param username the username identifying the user whose data is required.
     * @return UserDetails для Spring Security
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getUserRoles(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getUserRoles(Collection<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return new HashSet<SimpleGrantedAuthority>();
        }
        return getRolesAndPrivileges(roles)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    private Collection<String> getRolesAndPrivileges(Collection<Role> roles) {
        Set<String> result = new HashSet<>();
        for (Role role : roles) {
            result.add(role.getRole());
            result.addAll(
                    role.getAuthorities()
                            .stream()
                            .map(Authority::getAuthority)
                            .collect(Collectors.toSet()));
        }
        return result;
    }

    @Override
    public String getTokenByUsername(String username) {
        return jwtTokenUtil.generateToken(loadUserByUsername(username));
    }

    @Override
    public boolean existsUserByUsernameIgnoreCase(String username) {
        return userRepository.existsUserByUsernameIgnoreCase(username);
    }

    @Override
    public boolean existsUserByEmailIgnoreCase(String email) {
        return userRepository.existsUserByEmailIgnoreCase(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new UserNotFoundException(
                        String.format("Пользователь с адресом электронной почты '%s' не найден", email)
                )
        );
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                ()-> new UserNotFoundException(
                        String.format("Пользователь с именем пользователя '%s' не найден", username)
                )
        );
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                ()-> new UserNotFoundException(
                        String.format("Пользователь с идентификатором '%s' не найден", id)
                )
        );
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
