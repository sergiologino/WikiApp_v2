package ru.altacod.wikiapp.service;

import ru.altacod.wikiapp.entity.Organization;
import ru.altacod.wikiapp.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public Organization createOrganization(Organization organization) {
        // 1. Сохраняем организацию в схему admin
        Organization savedOrg = organizationRepository.save(organization);

        // 2. Создаем схему для организации
        createOrganizationSchema(savedOrg);

        // 3. Создаем пользователя admin с ролью root
        createAdminUser(savedOrg);

        return savedOrg;
    }

    private void createOrganizationSchema(Organization organization) {
        String schemaName = "org_" + organization.getCode(); // Например, org_1

        // Создаем новую схему
        entityManager.createNativeQuery("CREATE SCHEMA " + schemaName).executeUpdate();

        // Копируем структуру из шаблонной схемы
        copySchema("template", schemaName);
    }

    private void copySchema(String sourceSchema, String targetSchema) {
        // Получаем список таблиц из шаблонной схемы
        String getTablesQuery = "SELECT tablename FROM pg_tables WHERE schemaname = :schemaName";
        List<String> tables = entityManager.createNativeQuery(getTablesQuery)
                .setParameter("schemaName", sourceSchema)
                .getResultList();

        // Копируем каждую таблицу
        for (String table : tables) {
            String copyTableQuery = String.format("CREATE TABLE %s.%s AS TABLE %s.%s WITH NO DATA",
                    targetSchema, table, sourceSchema, table);
            entityManager.createNativeQuery(copyTableQuery).executeUpdate();
        }
    }

    private void createAdminUser(Organization organization) {
        String schemaName = "org_" + organization.getCode();

        // Создаем пользователя admin с ролью root в новой схеме
        String insertUserQuery = String.format(
                "INSERT INTO %s.user (id, username, password, active) VALUES (?, 'admin', 'admin', true)",
                schemaName
        );
        UUID userId = UUID.randomUUID();
        entityManager.createNativeQuery(insertUserQuery)
                .setParameter(1, userId)
                .executeUpdate();

        // Создаем роль root
        UUID roleId = UUID.randomUUID();
        String insertRoleQuery = String.format(
                "INSERT INTO %s.role (id, name, access_level) VALUES (?, 'root', 'FULL_ACCESS')",
                schemaName
        );
        entityManager.createNativeQuery(insertRoleQuery)
                .setParameter(1, roleId)
                .executeUpdate();

        // Связываем пользователя с ролью
        String insertUserRoleQuery = String.format(
                "INSERT INTO %s.user_roles (user_id, role_id) VALUES (?, ?)",
                schemaName
        );
        entityManager.createNativeQuery(insertUserRoleQuery)
                .setParameter(1, userId)
                .setParameter(2, roleId)
                .executeUpdate();
    }
}
