package ru.altacod.wikiapp.service;

import ru.altacod.wikiapp.entity.Role;
import ru.altacod.wikiapp.entity.Document;
import ru.altacod.wikiapp.entity.Space;
import ru.altacod.wikiapp.repository.RoleRepository;
import ru.altacod.wikiapp.repository.DocumentRepository;
import ru.altacod.wikiapp.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.Set;

/**
 * Сервис для работы с ролями.
 */
@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final DocumentRepository documentRepository;
    private final SpaceRepository spaceRepository;

    public RoleService(RoleRepository roleRepository, DocumentRepository documentRepository, SpaceRepository spaceRepository) {
        this.roleRepository = roleRepository;
        this.documentRepository = documentRepository;
        this.spaceRepository = spaceRepository;
    }

    /**
     * Создать новую роль.
     *
     * @param role объект роли
     * @return созданная роль
     */
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Удалить роль.
     *
     * @param id ID роли
     */
    public void deleteRole(UUID id) {
        roleRepository.deleteById(id);
    }

    /**
     * Привязать роль к документу.
     *
     * @param roleId     ID роли
     * @param documentId ID документа
     */
    public void assignRoleToDocument(UUID roleId, UUID documentId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Документ не найден"));

        role.getDocuments().add(document);
        roleRepository.save(role);
    }

    /**
     * Привязать роль к разделу.
     *
     * @param roleId  ID роли
     * @param spaceId ID раздела
     */
    public void assignRoleToSpace(UUID roleId, UUID spaceId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new RuntimeException("Раздел не найден"));

        role.getSpaces().add(space);
        roleRepository.save(role);
    }
}
