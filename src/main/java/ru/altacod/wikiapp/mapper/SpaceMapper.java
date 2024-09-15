package ru.altacod.wikiapp.mapper;

import ru.altacod.wikiapp.dto.SpaceDTO;
import ru.altacod.wikiapp.entity.Space;

/**
 * Маппер для преобразования между Space и SpaceDTO.
 */
public class SpaceMapper {

    public static SpaceDTO toDTO(Space space) {
        SpaceDTO dto = new SpaceDTO();
        dto.setId(space.getId());
        dto.setName(space.getName());
        dto.setActive(space.isActive());
        return dto;
    }

    public static Space toEntity(SpaceDTO dto) {
        Space space = new Space();
        space.setId(dto.getId());
        space.setName(dto.getName());
        space.setActive(dto.isActive());
        return space;
    }
}
