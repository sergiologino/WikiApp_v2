package ru.altacod.wikiapp.service;

import ru.altacod.wikiapp.entity.Space;
import ru.altacod.wikiapp.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с разделами (Spaces).
 */
@Service
public class SpaceService {

    private final SpaceRepository spaceRepository;

    public SpaceService(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    /**
     * Получить список активных разделов.
     *
     * @return список разделов
     */
    public List<Space> getActiveSpaces() {
        return spaceRepository.findByActive(true);
    }

    /**
     * Создать новый раздел.
     *
     * @param space объект раздела
     * @return созданный раздел
     */
    public Space createSpace(Space space) {
        return spaceRepository.save(space);
    }

    /**
     * Обновить раздел.
     *
     * @param space объект раздела
     * @return обновленный раздел
     */
    public Space updateSpace(Space space) {
        return spaceRepository.save(space);
    }

    /**
     * Деактивировать раздел.
     *
     * @param id ID раздела
     */
    public void deactivateSpace(UUID id) {
        Space space = spaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Раздел не найден"));
        space.setActive(false);
        spaceRepository.save(space);
    }
}

