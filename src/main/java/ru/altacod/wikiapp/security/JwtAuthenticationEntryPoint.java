package ru.altacod.wikiapp.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Обработчик ошибок аутентификации JWT.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // Возвращаем 401, если аутентификация не удалась
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Ошибка аутентификации: " + authException.getMessage());
    }
}
