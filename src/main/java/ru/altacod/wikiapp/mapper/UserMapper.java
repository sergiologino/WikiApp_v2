package ru.altacod.wikiapp.mapper;

import ru.altacod.wikiapp.dto.UserDTO;
import ru.altacod.wikiapp.entity.User;
import ru.altacod.wikiapp.entity.Role;
import java.util.stream.Collectors;

/**
 * Маппер для преобразования между User и UserDTO.
 */
public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPosition(user.getPosition());
        dto.setActive(user.isActive());
        dto.setDeleted(user.isDeleted());
        if (user.getRoles() != null) {
            dto.setRoleIds(user.getRoles().stream()
                    .map(Role::getId)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPosition(dto.getPosition());
        user.setActive(dto.isActive());
        user.setDeleted(dto.isDeleted());
        // Пароль будет захеширован в UserService
        return user;
    }
}
