package ru.altacod.wikiapp.service;

import ru.altacod.wikiapp.dto.UserDTO;
import ru.altacod.wikiapp.entity.User;
import ru.altacod.wikiapp.entity.Role;
import ru.altacod.wikiapp.mapper.UserMapper;
import ru.altacod.wikiapp.repository.UserRepository;
import ru.altacod.wikiapp.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис для работы с пользователями.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Создать нового пользователя.
     *
     * @param userDTO DTO пользователя
     * @return созданный пользователь в виде DTO
     */
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);

        // Генерация защищенного пароля
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    /**
     * Назначить роли пользователю.
     *
     * @param userId  ID пользователя
     * @param roleIds набор ID ролей
     */
    @Transactional
    public void assignRolesToUser(UUID userId, Set<UUID> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Set<Role> roles = roleRepository.findAllById(roleIds).stream().collect(Collectors.toSet());
        user.setRoles(roles);
        userRepository.save(user);
    }

    /**
     * Обновить информацию о пользователе.
     *
     * @param userDTO DTO пользователя
     * @return обновленный пользователь в виде DTO
     */
    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPosition(userDTO.getPosition());
        user.setActive(userDTO.isActive());
        user.setDeleted(userDTO.isDeleted());
        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    /**
     * Получить пользователя по ID.
     *
     * @param userId ID пользователя
     * @return пользователь в виде DTO
     */
    public UserDTO getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return UserMapper.toDTO(user);
    }
}
