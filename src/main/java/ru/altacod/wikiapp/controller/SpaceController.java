package ru.altacod.wikiapp.controller;

import ru.altacod.wikiapp.entity.Space;
import ru.altacod.wikiapp.service.SpaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для управления разделами (Spaces).
 */
@RestController
@RequestMapping("/api/spaces")
@Tag(name = "Spaces", description = "API для управления разделами")
public class SpaceController {

    private final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    /**
     * Получить список активных разделов.
     *
     * @return список разделов
     */
    @GetMapping
    @Operation(summary = "Получить список разделов", description = "Возвращает список всех активных разделов, доступных пользователю согласно его роли.")
    public List<Space> getActiveSpaces() {
        return spaceService.getActiveSpaces();
    }

    /**
     * Создать новый раздел.
     *
     * @param space объект раздела
     * @return созданный раздел
     */
    @PostMapping
    @Operation(summary = "Создать раздел", description = "Создает новый раздел.")
    public Space createSpace(@RequestBody Space space) {
        return spaceService.createSpace(space);
    }

    /**
     * Обновить раздел.
     *
     * @param id    ID раздела
     * @param space объект раздела
     * @return обновленный раздел
     */
    @PutMapping("/{id}")
    @Operation(summary = "Обновить раздел", description = "Обновляет существующий раздел.")
    public Space updateSpace(@PathVariable UUID id, @RequestBody Space space) {
        space.setId(id);
        return spaceService.updateSpace(space);
    }

    /**
     * Деактивировать раздел.
     *
     * @param id ID раздела
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Деактивировать раздел", description = "Помечает раздел как неактивный.")
    public void deactivateSpace(@PathVariable UUID id) {
        spaceService.deactivateSpace(id);
    }
}
