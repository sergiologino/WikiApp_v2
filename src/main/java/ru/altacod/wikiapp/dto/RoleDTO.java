package ru.altacod.wikiapp.dto;

import java.util.Set;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO для сущности Role.
 */
public class RoleDTO {

    private UUID id;

    @NotBlank(message = "Имя роли не должно быть пустым")
    private String name;

    @NotBlank(message = "Уровень доступа не должен быть пустым")
    private String accessLevel;

    private Set<UUID> userIds;
    private Set<UUID> documentIds;
    private Set<UUID> spaceIds;

    // Геттеры и сеттеры

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Set<UUID> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<UUID> userIds) {
        this.userIds = userIds;
    }

    public Set<UUID> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(Set<UUID> documentIds) {
        this.documentIds = documentIds;
    }

    public Set<UUID> getSpaceIds() {
        return spaceIds;
    }

    public void setSpaceIds(Set<UUID> spaceIds) {
        this.spaceIds = spaceIds;
    }
}
