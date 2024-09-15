package ru.altacod.wikiapp.dto;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO для сущности Space.
 */
public class SpaceDTO {

    private UUID id;

    @NotBlank(message = "Название раздела не должно быть пустым")
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
