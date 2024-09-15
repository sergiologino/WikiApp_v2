package ru.altacod.wikiapp.mapper;

import ru.altacod.wikiapp.dto.DocumentVersionDTO;
import ru.altacod.wikiapp.entity.DocumentVersion;
import ru.altacod.wikiapp.entity.Document;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Маппер для преобразования между DocumentVersion и DocumentVersionDTO.
 */
public class DocumentVersionMapper {

    /**
     * Преобразует сущность DocumentVersion в DocumentVersionDTO.
     *
     * @param documentVersion сущность DocumentVersion
     * @return объект DocumentVersionDTO
     */
    public static DocumentVersionDTO toDTO(DocumentVersion documentVersion) {
        if (documentVersion == null) {
            return null;
        }
        DocumentVersionDTO dto = new DocumentVersionDTO();
        dto.setId(documentVersion.getId());
        dto.setVersionNumber(documentVersion.getVersionNumber());
        dto.setContent(documentVersion.getContent());
        dto.setCreatedAt(documentVersion.getCreatedAt());
        dto.setDocumentId(documentVersion.getDocument().getId());
        return dto;
    }

    /**
     * Преобразует объект DocumentVersionDTO в сущность DocumentVersion.
     *
     * @param dto      объект DocumentVersionDTO
     * @param document сущность Document, к которой относится версия
     * @return сущность DocumentVersion
     */
    public static DocumentVersion toEntity(DocumentVersionDTO dto, Optional<Document> document) {
        if (dto == null || document == null) {
            return null;
        }
        DocumentVersion documentVersion = new DocumentVersion();
        documentVersion.setId(dto.getId() != null ? dto.getId() : null); // Если ID не указан, генерируется автоматически в конструкторе
        documentVersion.setVersionNumber(dto.getVersionNumber());
        documentVersion.setContent(dto.getContent());
        documentVersion.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now());
        documentVersion.setDocument(document);
        return documentVersion;
    }
}
