package ru.altacod.wikiapp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.altacod.wikiapp.entity.Organization;
import ru.altacod.wikiapp.entity.User;

import java.util.Collection;
import java.util.UUID;

/**
 * Класс, представляющий пользователя для Spring Security, с дополнительной информацией об организации.
 */
public class CustomUserDetails implements UserDetails {

    private UUID id;
    private String username;
    private String password;
    private boolean active;
    private Organization organization;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.organization = user.getOrganization();
        this.authorities = authorities;
    }

    public Organization getOrganization() {
        return organization;
    }

    // Реализация методов UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    // Используем имя пользователя для идентификации
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
