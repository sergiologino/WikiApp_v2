package ru.altacod.wikiapp.dto;

import java.util.UUID;

/**
 * DTO для сущности Space.
 */
public class SpaceDTO {

    private UUID id;
    private String name;
    private boolean active;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
