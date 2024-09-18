package ru.altacod.wikiapp.service;

import ru.altacod.wikiapp.dto.RoleDTO;
import ru.altacod.wikiapp.entity.Role;
import ru.altacod.wikiapp.entity.User;
import ru.altacod.wikiapp.entity.Document;
import ru.altacod.wikiapp.entity.Space;
import ru.altacod.wikiapp.mapper.RoleMapper;
import ru.altacod.wikiapp.repository.RoleRepository;
import ru.altacod.wikiapp.repository.UserRepository;
import ru.altacod.wikiapp.repository.DocumentRepository;
import ru.altacod.wikiapp.repository.SpaceRepository;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис для работы с ролями.
 */
@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final SpaceRepository spaceRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository,
                       DocumentRepository documentRepository, SpaceRepository spaceRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
        this.spaceRepository = spaceRepository;
    }

    /**
     * Создать новую роль.
     *
     * @param roleDTO DTO роли
     * @return созданная роль в виде DTO
     */
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = RoleMapper.toEntity(roleDTO);
        roleRepository.save(role);
        return RoleMapper.toDTO(role);
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
     * Назначить роль пользователям.
     *
     * @param roleId  ID роли
     * @param userIds набор ID пользователей
     */
    public void assignRoleToUsers(UUID roleId, Set<UUID> userIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));
        Set<User> users = userRepository.findAllById(userIds).stream().collect(Collectors.toSet());
        role.setUsers(users);
        roleRepository.save(role);
    }

    /**
     * Назначить роль документам.
     *
     * @param roleId      ID роли
     * @param documentIds набор ID документов
     */
    public void assignRoleToDocuments(UUID roleId, Set<UUID> documentIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));
        Set<Document> documents = documentRepository.findAllById(documentIds).stream().collect(Collectors.toSet());
        role.setDocuments(documents);
        roleRepository.save(role);
    }

    /**
     * Назначить роль документу.
     *
     * @param roleId      ID роли
     * @param documentId набор ID документов
     */
    public void assignRoleToDocument(UUID roleId, UUID documentId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Документ не найден"));

        // Добавляем документ к роли
        role.getDocuments().add(document);

        // Сохраняем изменения
        roleRepository.save(role);
    }

    /**
     * Назначить роль разделам.
     *
     * @param roleId   ID роли
     * @param spaceIds набор ID разделов
     */
    public void assignRoleToSpaces(UUID roleId, Set<UUID> spaceIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));
        Set<Space> spaces = spaceRepository.findAllById(spaceIds).stream().collect(Collectors.toSet());
        role.setSpaces(spaces);
        roleRepository.save(role);
    }
    /**
     * Назначить роль разделам.
     *
     * @param roleId   ID роли
     * @param spaceId набор ID разделов
     */
    public void assignRoleToSpace(UUID roleId, UUID spaceId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));
        Space space = spaceRepository.findById(spaceId)
         .orElseThrow(() -> new RuntimeException("Роль не найдена"));
        Set<Space> spaces = new HashSet<>();
        spaces.add(space);
        role.setSpaces(spaces);
        roleRepository.save(role);
    }

    /**
     * Обновить роль.
     *
     * @param roleDTO DTO роли
     * @return обновленная роль в виде DTO
     */
    public RoleDTO updateRole(RoleDTO roleDTO) {
        Role role = roleRepository.findById(roleDTO.getId())
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));
        role.setName(roleDTO.getName());
        role.setAccessLevel(roleDTO.getAccessLevel());
        roleRepository.save(role);
        return RoleMapper.toDTO(role);
    }

    /**
     * Получить роль по ID.
     *
     * @param roleId ID роли
     * @return роль в виде DTO
     */
    public RoleDTO getRoleById(UUID roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));
        return RoleMapper.toDTO(role);
    }
}
