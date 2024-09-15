package ru.altacod.wikiapp.repository;

import ru.altacod.wikiapp.entity.DocumentVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с сущностью DocumentVersion.
 */
public interface DocumentVersionRepository extends JpaRepository<DocumentVersion, UUID> {

    /**
     * Получить список версий документа по ID документа.
     *
     * @param documentId ID документа
     * @return список версий
     */
    List<DocumentVersion> findByDocumentId(UUID documentId);

    /**
     * Найти версию документа по ID документа и номеру версии.
     *
     * @param documentId   ID документа
     * @param versionNumber номер версии
     * @return версия документа
     */
    Optional<DocumentVersion> findByDocumentIdAndVersionNumber(UUID documentId, int versionNumber);

    List<DocumentVersion> findByDocumentIdAndVersionNumberIn(UUID documentId, List<Integer> versionNumbers);
}

