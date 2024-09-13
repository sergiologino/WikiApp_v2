package ru.altacod.wikiapp.controller;

import ru.altacod.wikiapp.dto.OrganizationDTO;
import ru.altacod.wikiapp.service.OrganizationService;
import ru.altacod.wikiapp.mapper.OrganizationMapper;
import ru.altacod.wikiapp.entity.Organization;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/organizations")
@Tag(name = "Organizations", description = "API для управления организациями")
@Validated
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    @Operation(summary = "Создать организацию", description = "Создает новую организацию.")
    public ResponseEntity<OrganizationDTO> createOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) {
        Organization organization = OrganizationMapper.toEntity(organizationDTO);
        Organization createdOrg = organizationService.createOrganization(organization);
        OrganizationDTO createdOrgDTO = OrganizationMapper.toDTO(createdOrg);
        return ResponseEntity.ok(createdOrgDTO);
    }

    // Обработка ошибок будет в GlobalExceptionHandler
}
