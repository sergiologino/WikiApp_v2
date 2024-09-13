package ru.altacod.wikiapp.dto;

import java.util.Set;
import java.util.UUID;

/**
 * DTO для сущности Role.
 */
public class RoleDTO {

    private UUID id;
    private String name;
    private String accessLevel;
    private Set<UUID> userIds;

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
}
