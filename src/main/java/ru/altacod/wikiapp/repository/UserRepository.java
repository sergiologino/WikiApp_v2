package ru.altacod.wikiapp.repository;

import ru.altacod.wikiapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Найти пользователя по имени пользователя.
     *
     * @param username имя пользователя
     * @return опционально пользователь
     */
    Optional<User> findByUsername(String username);
}
