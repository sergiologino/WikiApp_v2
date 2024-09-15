package ru.altacod.wikiapp.mapper;

import ru.altacod.wikiapp.dto.DocumentDTO;
import ru.altacod.wikiapp.entity.Document;

import java.util.UUID;

/**
 * Маппер для преобразования между Document и DocumentDTO.
 */
public class DocumentMapper {

    public static DocumentDTO toDTO(Document document) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(document.getId());
        dto.setTitle(document.getTitle());
        dto.setContent(document.getContent());
        dto.setDeleted(document.isDeleted());

        if (document.getParent() != null) {
            dto.setParentId(document.getParent().getId());
        }

        if (document.getSpace() != null) {
            dto.setSpaceId(document.getSpace().getId());
        }

        return dto;
    }

    public static Document toEntity(DocumentDTO dto) {
        Document document = new Document();
        document.setId(dto.getId());
        document.setTitle(dto.getTitle());
        document.setContent(dto.getContent());
        document.setDeleted(dto.isDeleted());
        // Связи с родительским документом и разделом устанавливаются в сервисе
        return document;
    }
}
