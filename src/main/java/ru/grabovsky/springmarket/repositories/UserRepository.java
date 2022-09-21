package ru.grabovsky.springmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grabovsky.springmarket.entity.auth.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsUserByUsernameIgnoreCase(String username);
    boolean existsUserByEmailIgnoreCase(String email);
    Optional<User> findByEmail(String email);
}
