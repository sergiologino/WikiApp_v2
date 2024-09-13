package ru.altacod.wikiapp.repository;

import ru.altacod.wikiapp.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    // Методы поиска по необходимости
}

