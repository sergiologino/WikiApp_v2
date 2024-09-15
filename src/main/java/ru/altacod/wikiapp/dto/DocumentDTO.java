package ru.altacod.wikiapp.dto;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO для сущности Document.
 */
public class DocumentDTO {

    private UUID id;

    @NotBlank(message = "Заголовок не должен быть пустым")
    private String title;

    private String content;
    private boolean deleted;
    private UUID parentId;
    private UUID spaceId;

    // Геттеры и сеттеры

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public UUID getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(UUID spaceId) {
        this.spaceId = spaceId;
    }
}
