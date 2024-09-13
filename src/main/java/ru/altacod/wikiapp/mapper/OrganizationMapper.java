package ru.altacod.wikiapp.mapper;

import ru.altacod.wikiapp.dto.OrganizationDTO;
import ru.altacod.wikiapp.entity.Organization;

/**
 * Маппер для преобразования между Organization и OrganizationDTO.
 */
public class OrganizationMapper {

    public static OrganizationDTO toDTO(Organization organization) {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setId(organization.getId().toString());
        dto.setName(organization.getName());
        dto.setInn(organization.getInn());
        dto.setAvatarPath(organization.getAvatarPath());
        return dto;
    }

    public static Organization toEntity(OrganizationDTO dto) {
        Organization organization = new Organization();
        organization.setName(dto.getName());
        organization.setInn(dto.getInn());
        organization.setAvatarPath(dto.getAvatarPath());
        return organization;
    }
}
