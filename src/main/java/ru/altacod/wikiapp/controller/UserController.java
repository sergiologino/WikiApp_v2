package ru.altacod.wikiapp.controller;

import ru.altacod.wikiapp.dto.UserDTO;
import ru.altacod.wikiapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

/**
 * Контроллер для управления пользователями.
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "API для управления пользователями")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Создать нового пользователя.
     *
     * @param userDTO DTO пользователя
     * @return созданный пользователь в виде DTO
     */
    @PostMapping
    @Operation(summary = "Создать пользователя", description = "Создает нового пользователя.")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    /**
     * Назначить роли пользователю.
     *
     * @param userId  ID пользователя
     * @param roleIds набор ID ролей
     */
    @PostMapping("/{userId}/roles")
    @Operation(summary = "Назначить роли пользователю", description = "Назначает роли пользователю.")
    public void assignRolesToUser(@PathVariable UUID userId, @RequestBody Set<UUID> roleIds) {
        userService.assignRolesToUser(userId, roleIds);
    }

    /**
     * Обновить информацию о пользователе.
     *
     * @param userId  ID пользователя
     * @param userDTO DTO пользователя
     * @return обновленный пользователь в виде DTO
     */
    @PutMapping("/{userId}")
    @Operation(summary = "Обновить пользователя", description = "Обновляет информацию о пользователе.")
    public UserDTO updateUser(@PathVariable UUID userId, @RequestBody UserDTO userDTO) {
        userDTO.setId(userId);
        return userService.updateUser(userDTO);
    }

    /**
     * Получить пользователя по ID.
     *
     * @param userId ID пользователя
     * @return пользователь в виде DTO
     */
    @GetMapping("/{userId}")
    @Operation(summary = "Получить пользователя", description = "Возвращает пользователя по ID.")
    public UserDTO getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }
}
