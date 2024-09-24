package ru.altacod.wikiapp.entity;

import jakarta.persistence.*;

import java.util.UUID;
import java.util.Set;

/**
 * Сущность "Document" представляет документ в системе.
 */
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    /**
     * Контент документа в формате HTML.
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean deleted = false;

    /**
     * Родительский документ для построения иерархии.
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Document parent;

    /**
     * Раздел (Space), к которому принадлежит документ.
     */
    @ManyToOne
    @JoinColumn(name = "space_id")
    private Space space;

    /**
     * Связь с версиями документа.
     */
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private Set<DocumentVersion> versions;

    public Document() {
        this.id = UUID.randomUUID();
    }

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

    public Document getParent() {
        return parent;
    }

    public void setParent(Document parent) {
        this.parent = parent;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Set<DocumentVersion> getVersions() {
        return versions;
    }

    public void setVersions(Set<DocumentVersion> versions) {
        this.versions = versions;
    }
}
