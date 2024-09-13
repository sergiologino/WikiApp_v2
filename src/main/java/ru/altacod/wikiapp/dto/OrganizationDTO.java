package ru.altacod.wikiapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO для сущности Organization.
 */
public class OrganizationDTO {

    private String id;

    @NotBlank(message = "Наименование организации не должно быть пустым")
    private String name;

    @Size(max = 12, message = "ИНН должен содержать не более 12 символов")
    private String inn;

    private String avatarPath;

    // Геттеры и сеттеры

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotBlank(message = "Наименование организации не должно быть пустым") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Наименование организации не должно быть пустым") String name) {
        this.name = name;
    }

    public @Size(max = 12, message = "ИНН должен содержать не более 12 символов") String getInn() {
        return inn;
    }

    public void setInn(@Size(max = 12, message = "ИНН должен содержать не более 12 символов") String inn) {
        this.inn = inn;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    // ...
}
