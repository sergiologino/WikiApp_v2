package ru.altacod.wikiapp.repository;

import ru.altacod.wikiapp.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для работы с сущностью Space.
 */
public interface SpaceRepository extends JpaRepository<Space, UUID> {

    /**
     * Получить список активных разделов.
     *
     * @param active признак активности
     * @return список разделов
     */
    List<Space> findByActive(boolean active);
}
