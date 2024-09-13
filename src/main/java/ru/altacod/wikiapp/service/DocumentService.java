package ru.altacod.wikiapp.service;

import ru.altacod.wikiapp.entity.Document;
import ru.altacod.wikiapp.entity.DocumentVersion;
import ru.altacod.wikiapp.repository.DocumentRepository;
import ru.altacod.wikiapp.repository.DocumentVersionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис для работы с документами.
 */
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentVersionRepository documentVersionRepository;

    public DocumentService(DocumentRepository documentRepository, DocumentVersionRepository documentVersionRepository) {
        this.documentRepository = documentRepository;
        this.documentVersionRepository = documentVersionRepository;
    }

    /**
     * Создать новый документ.
     *
     * @param document объект документа
     * @return созданный документ
     */
    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    /**
     * Получить документ по ID.
     *
     * @param id ID документа
     * @return документ
     */
    public Document getDocumentById(UUID id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Документ не найден"));
    }

    /**
     * Пометить документ как удаленный.
     *
     * @param id ID документа
     */
    public void deleteDocument(UUID id) {
        Document document = getDocumentById(id);
        document.setDeleted(true);
        documentRepository.save(document);
    }

    /**
     * Получить черновик документа.
     *
     * @param documentId ID документа
     * @return черновик
     */
    public DocumentVersion getDraft(UUID documentId) {
        return documentVersionRepository.findByDocumentIdAndVersionNumber(documentId, -1)
                .orElseThrow(() -> new RuntimeException("Черновик не найден"));
    }

    /**
     * Сохранить черновик документа.
     *
     * @param documentId ID документа
     * @param content    контент черновика
     * @return сохраненный черновик
     */
    public DocumentVersion saveDraft(UUID documentId, String content) {
        Document document = getDocumentById(documentId);
        DocumentVersion draft = documentVersionRepository.findByDocumentIdAndVersionNumber(documentId, -1)
                .orElse(new DocumentVersion());

        draft.setDocument(document);
        draft.setVersionNumber(-1);
        draft.setContent(content);

        return documentVersionRepository.save(draft);
    }

    /**
     * Получить все версии документа.
     *
     * @param documentId ID документа
     * @return список версий
     */
    public List<DocumentVersion> getDocumentVersions(UUID documentId) {
        return documentVersionRepository.findByDocumentId(documentId);
    }

    /**
     * Получить выбранные версии документа.
     *
     * @param documentId     ID документа
     * @param versionNumbers список номеров версий
     * @return список версий
     */
    public List<DocumentVersion> getSelectedVersions(UUID documentId, List<Integer> versionNumbers) {
        return documentVersionRepository.findByDocumentId(documentId).stream()
                .filter(version -> versionNumbers.contains(version.getVersionNumber()))
                .collect(Collectors.toList());
    }
}
