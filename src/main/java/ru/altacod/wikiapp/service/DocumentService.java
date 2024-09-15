package ru.altacod.wikiapp.service;

import ru.altacod.wikiapp.dto.DocumentDTO;
import ru.altacod.wikiapp.dto.DocumentVersionDTO;
import ru.altacod.wikiapp.entity.Document;
import ru.altacod.wikiapp.entity.DocumentVersion;
import ru.altacod.wikiapp.entity.Space;
import ru.altacod.wikiapp.mapper.DocumentMapper;
import ru.altacod.wikiapp.mapper.DocumentVersionMapper;
import ru.altacod.wikiapp.repository.DocumentRepository;
import ru.altacod.wikiapp.repository.DocumentVersionRepository;
import ru.altacod.wikiapp.repository.SpaceRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис для работы с документами.
 */
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentVersionRepository documentVersionRepository;
    private final SpaceRepository spaceRepository;

    public DocumentService(DocumentRepository documentRepository,
                           DocumentVersionRepository documentVersionRepository,
                           SpaceRepository spaceRepository) {
        this.documentRepository = documentRepository;
        this.documentVersionRepository = documentVersionRepository;
        this.spaceRepository = spaceRepository;
    }

    /**
     * Создать новый документ.
     *
     * @param documentDTO DTO документа
     * @return созданный документ в виде DTO
     */
    public DocumentDTO createDocument(DocumentDTO documentDTO) {
        Document document = DocumentMapper.toEntity(documentDTO);

        // Устанавливаем связи
        if (documentDTO.getSpaceId() != null) {
            Space space = spaceRepository.findById(documentDTO.getSpaceId())
                    .orElseThrow(() -> new RuntimeException("Раздел не найден"));
            document.setSpace(space);
        }

        if (documentDTO.getParentId() != null) {
            Document parent = documentRepository.findById(documentDTO.getParentId())
                    .orElseThrow(() -> new RuntimeException("Родительский документ не найден"));
            document.setParent(parent);
        }

        documentRepository.save(document);
        return DocumentMapper.toDTO(document);
    }

    /**
     * Получить документ по ID.
     *
     * @param id ID документа
     * @return документ в виде DTO
     */
    public DocumentDTO getDocumentById(UUID id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Документ не найден"));
        return DocumentMapper.toDTO(document);
    }

    /**
     * Пометить документ как удаленный.
     *
     * @param id ID документа
     */
    public void deleteDocument(UUID id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Документ не найден"));
        document.setDeleted(true);
        documentRepository.save(document);
    }

    /**
     * Сохранить черновик документа.
     *
     * @param documentId ID документа
     * @param dto    контент черновика
     * @return сохраненный черновик в виде DTO
     */
    public DocumentVersionDTO saveDraft(UUID documentId, DocumentVersionDTO dto) {
        Optional<Document> document = documentRepository.findById(documentId);
        DocumentVersion draft = documentVersionRepository.findByDocumentIdAndVersionNumber(documentId, -1)
                .orElse(new DocumentVersion());

        draft = DocumentVersionMapper.toEntity(dto, document);
        draft.setVersionNumber(-1); // Устанавливаем версию черновика
        documentVersionRepository.save(draft);

        return DocumentVersionMapper.toDTO(draft);
    }

    /**
     * Получить черновик документа.
     *
     * @param documentId ID документа
     * @return черновик в виде DTO
     */
    public DocumentVersionDTO getDraft(UUID documentId) {
        DocumentVersion draft = documentVersionRepository.findByDocumentIdAndVersionNumber(documentId, -1)
                .orElseThrow(() -> new RuntimeException("Черновик не найден"));
        return DocumentVersionMapper.toDTO(draft);
    }

    public List<DocumentVersionDTO> getDocumentVersions(UUID documentId) {
        return documentVersionRepository.findByDocumentId(documentId).stream()
                .map(DocumentVersionMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<DocumentVersionDTO> getSelectedVersions(UUID documentId, List<Integer> versionNumbers) {
        // Проверяем, что список номеров версий не пустой
        if (versionNumbers == null || versionNumbers.isEmpty()) {
            throw new IllegalArgumentException("Список номеров версий не должен быть пустым");
        }

        // Проверяем существование документа
        DocumentDTO document = getDocumentById(documentId);

        // Получаем версии из репозитория
        List<DocumentVersion> versions = documentVersionRepository.findByDocumentIdAndVersionNumberIn(documentId, versionNumbers);

        if (versions.isEmpty()) {
            throw new RuntimeException("Запрошенные версии не найдены");
        }

        // Преобразуем сущности в DTO
        return versions.stream()
                .map(DocumentVersionMapper::toDTO)
                .collect(Collectors.toList());
    }
}



