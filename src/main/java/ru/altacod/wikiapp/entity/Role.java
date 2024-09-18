package ru.altacod.wikiapp.entity;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

/**
 * Сущность "Role" представляет роль пользователя в системе.
 */
@Entity
public class Role {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Признак доступа роли:
     * - "READ_ONLY" — только чтение
     * - "FULL_ACCESS" — разрешено все
     * - "NO_ACCESS" — запрещено все
     */
    @Column(nullable = false)
    private String accessLevel;

    /**
     * Связь с пользователями, имеющими эту роль.
     */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    /**
     * Связь с документами, к которым привязана эта роль.
     */
    @ManyToMany
    @JoinTable(
            name = "role_documents",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private Set<Document> documents;

    /**
     * Связь с разделами (Space), к которым привязана эта роль.
     */
    @ManyToMany
    @JoinTable(
            name = "role_spaces",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "space_id")
    )
    private Set<Space> spaces;

    public Role() {
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

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(Set<Space> spaces) {
        this.spaces = spaces;
    }
//    public void setSpace(Space space) {
//        this.spaces = space;
//    }
}
