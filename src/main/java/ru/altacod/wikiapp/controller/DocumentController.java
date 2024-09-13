package ru.altacod.wikiapp.controller;

import ru.altacod.wikiapp.entity.Document;
import ru.altacod.wikiapp.entity.DocumentVersion;
import ru.altacod.wikiapp.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для управления документами.
 */
@RestController
@RequestMapping("/api/documents")
@Tag(name = "Documents", description = "API для управления документами")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Создать новый документ.
     *
     * @param document объект документа
     * @return созданный документ
     */
    @PostMapping
    @Operation(summary = "Создать документ", description = "Создает новый документ.")
    public Document createDocument(@RequestBody Document document) {
        return documentService.createDocument(document);
    }

    /**
     * Получить документ по ID.
     *
     * @param id ID документа
     * @return документ
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получить документ", description = "Возвращает документ по ID.")
    public Document getDocument(@PathVariable UUID id) {
        return documentService.getDocumentById(id);
    }

    /**
     * Пометить документ как удаленный.
     *
     * @param id ID документа
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить документ", description = "Помечает документ как удаленный.")
    public void deleteDocument(@PathVariable UUID id) {
        documentService.deleteDocument(id);
    }

    /**
     * Получить черновик документа.
     *
     * @param id ID документа
     * @return черновик
     */
    @GetMapping("/{id}/draft")
    @Operation(summary = "Получить черновик", description = "Возвращает черновик документа.")
    public DocumentVersion getDraft(@PathVariable UUID id) {
        return documentService.getDraft(id);
    }

    /**
     * Сохранить черновик документа.
     *
     * @param id      ID документа
     * @param content контент черновика
     * @return сохраненный черновик
     */
    @PostMapping("/{id}/draft")
    @Operation(summary = "Сохранить черновик", description = "Сохраняет черновик документа.")
    public DocumentVersion saveDraft(@PathVariable UUID id, @RequestBody String content) {
        return documentService.saveDraft(id, content);
    }

    /**
     * Получить все версии документа.
     *
     * @param id ID документа
     * @return список версий
     */
    @GetMapping("/{id}/versions")
    @Operation(summary = "Получить версии документа", description = "Возвращает все версии документа.")
    public List<DocumentVersion> getDocumentVersions(@PathVariable UUID id) {
        return documentService.getDocumentVersions(id);
    }

    /**
     * Получить выбранные версии документа.
     *
     * @param id            ID документа
     * @param versionNumbers список номеров версий
     * @return список версий
     */
    @GetMapping("/{id}/versions/selected")
    @Operation(summary = "Получить выбранные версии", description = "Возвращает выбранные версии документа.")
    public List<DocumentVersion> getSelectedVersions(@PathVariable UUID id, @RequestParam List<Integer> versionNumbers) {
        return documentService.getSelectedVersions(id, versionNumbers);
    }
}
