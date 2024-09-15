package ru.altacod.wikiapp.mapper;

import ru.altacod.wikiapp.dto.RoleDTO;
import ru.altacod.wikiapp.entity.Role;
import ru.altacod.wikiapp.entity.User;
import ru.altacod.wikiapp.entity.Document;
import ru.altacod.wikiapp.entity.Space;

import java.util.stream.Collectors;

/**
 * Маппер для преобразования между Role и RoleDTO.
 */
public class RoleMapper {

    public static RoleDTO toDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setAccessLevel(role.getAccessLevel());

        if (role.getUsers() != null) {
            dto.setUserIds(role.getUsers().stream()
                    .map(User::getId)
                    .collect(Collectors.toSet()));
        }

        if (role.getDocuments() != null) {
            dto.setDocumentIds(role.getDocuments().stream()
                    .map(Document::getId)
                    .collect(Collectors.toSet()));
        }

        if (role.getSpaces() != null) {
            dto.setSpaceIds(role.getSpaces().stream()
                    .map(Space::getId)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

    public static Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setAccessLevel(dto.getAccessLevel());
        // Связи с пользователями, документами и разделами устанавливаются в сервисе
        return role;
    }
}
