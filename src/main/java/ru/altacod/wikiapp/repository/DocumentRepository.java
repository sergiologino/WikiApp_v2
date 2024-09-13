package ru.altacod.wikiapp.repository;

import ru.altacod.wikiapp.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для работы с сущностью Document.
 */
public interface DocumentRepository extends JpaRepository<Document, UUID> {

    /**
     * Получить список документов раздела.
     *
     * @param spaceId ID раздела
     * @param deleted признак удаления
     * @return список документов
     */
    List<Document> findBySpaceIdAndDeleted(UUID spaceId, boolean deleted);
}
