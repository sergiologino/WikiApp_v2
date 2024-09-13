package ru.altacod.wikiapp.repository;

import ru.altacod.wikiapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Репозиторий для работы с сущностью Role.
 */
public interface RoleRepository extends JpaRepository<Role, UUID> {

    /**
     * Найти роль по имени.
     *
     * @param name имя роли
     * @return роль
     */
    Role findByName(String name);
}
