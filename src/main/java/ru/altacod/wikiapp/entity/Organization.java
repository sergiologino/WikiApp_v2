package ru.altacod.wikiapp.entity;

import jakarta.persistence.*;
import java.util.UUID;

/**
 * Сущность "Organization" представляет организацию (клиента системы).
 */
@Entity
@Table(name = "organization", schema = "admin")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true) // Указываем, что поле должно быть непустым и уникальным
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_code_gen")
    @SequenceGenerator(name = "org_code_gen", sequenceName = "organization_code_seq", allocationSize = 1)
    private Long code; // Уникальный числовой код с автоинкрементом

    @Column(nullable = false)
    private String name; // Наименование организации

    private String inn; // ИНН организации (необязательное поле)

    private String avatarPath; // Путь к файлу с аватаром

    public Organization() {
        this.id = UUID.randomUUID();
    }

    // Геттеры и сеттеры

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
