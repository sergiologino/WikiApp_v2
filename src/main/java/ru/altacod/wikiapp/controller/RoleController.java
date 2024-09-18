package ru.altacod.wikiapp.controller;

import ru.altacod.wikiapp.dto.RoleDTO;
import ru.altacod.wikiapp.entity.Role;
import ru.altacod.wikiapp.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Контроллер для управления ролями.
 */
@RestController
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "API для управления ролями")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Создать новую роль.
     *
     * @param role объект роли
     * @return созданная роль
     */
    @PostMapping
    @Operation(summary = "Создать роль", description = "Создает новую роль.")
    public RoleDTO createRole(@RequestBody RoleDTO role) {
        return roleService.createRole(role);
    }

    /**
     * Удалить роль.
     *
     * @param id ID роли
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить роль", description = "Удаляет роль.")
    public void deleteRole(@PathVariable UUID id) {
        roleService.deleteRole(id);
    }

    /**
     * Привязать роль к документу.
     *
     * @param roleId     ID роли
     * @param documentId ID документа
     */
    @PostMapping("/{roleId}/documents/{documentId}")
    @Operation(summary = "Привязать роль к документу", description = "Привязывает роль к документу с определенным уровнем доступа.")
    public void assignRoleToDocument(@PathVariable UUID roleId, @PathVariable UUID documentId) {
        roleService.assignRoleToDocument(roleId, documentId);
    }

    /**
     * Привязать роль к разделу.
     *
     * @param roleId  ID роли
     * @param spaceId ID раздела
     */
    @PostMapping("/{roleId}/spaces/{spaceId}")
    @Operation(summary = "Привязать роль к разделу", description = "Привязывает роль к разделу с определенным уровнем доступа.")
    public void assignRoleToSpace(@PathVariable UUID roleId, @PathVariable UUID spaceId) {
        roleService.assignRoleToSpace(roleId, spaceId);
    }
}
