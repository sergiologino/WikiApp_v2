package ru.altacod.wikiapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import java.util.Set;
import java.util.UUID;

/**
 * DTO для сущности User.
 */
public class UserDTO {

    private UUID id;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;

    @Email(message = "Некорректный email")
    private String email;

    private String position;
    private boolean active;
    private boolean deleted;

    private Set<UUID> roleIds; // Связь с ролями пользователя

    // Геттеры и сеттеры

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Set<UUID> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<UUID> roleIds) {
        this.roleIds = roleIds;
    }
}
