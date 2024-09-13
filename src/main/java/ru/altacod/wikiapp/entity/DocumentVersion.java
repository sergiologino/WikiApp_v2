package ru.altacod.wikiapp.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Сущность "DocumentVersion" представляет версию документа.
 */
@Entity
public class DocumentVersion {

    @Id
    private UUID id;

    /**
     * Номер версии документа. Для черновика используем версию -1.
     */
    private int versionNumber;

    /**
     * Контент версии документа в формате HTML.
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * Дата создания версии.
     */
    private LocalDateTime createdAt;

    /**
     * Связь с документом.
     */
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    public DocumentVersion() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    // Геттеры и сеттеры

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}

