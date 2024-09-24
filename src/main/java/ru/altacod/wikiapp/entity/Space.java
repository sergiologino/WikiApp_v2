package ru.altacod.wikiapp.entity;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

/**
 * Сущность "Space" представляет раздел, аналогичный "Spaces" в Confluence.
 */
@Entity
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    /**
     * Признак активности раздела.
     */
    private boolean active = true;

    /**
     * Связь с документами, принадлежащими этому разделу.
     */
    @OneToMany(mappedBy = "space")
    private Set<Document> documents;

    public Space() {
        this.id = UUID.randomUUID();
    }

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

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
}
