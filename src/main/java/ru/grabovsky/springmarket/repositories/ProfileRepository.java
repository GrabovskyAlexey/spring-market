package ru.grabovsky.springmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.grabovsky.springmarket.entity.auth.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
