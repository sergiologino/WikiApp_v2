package ru.altacod.wikiapp.filter;

import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;

import ru.altacod.wikiapp.entity.User;
import ru.altacod.wikiapp.entity.Organization;
import ru.altacod.wikiapp.repository.UserRepository;

@Component
public class SchemaFilter implements Filter {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;

    public SchemaFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {
                String username = authentication.getName();
                // Получаем организацию пользователя
                Organization organization = getOrganizationByUsername(username);

                if (organization != null) {
                    String schemaName = "org_" + organization.getCode();
                    // Устанавливаем схему для текущего сеанса
                    setSchema(schemaName);
                } else {
                    throw new RuntimeException("Организация не найдена для пользователя");
                }
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            // Логируем ошибку и возвращаем ответ с ошибкой
            e.printStackTrace();
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка переключения схемы: " + e.getMessage());
        } finally {
            // После обработки запроса сбрасываем схему
            resetSchema();
        }
    }

    private void setSchema(String schemaName) throws SQLException {
        Session session = entityManager.unwrap(Session.class);
        session.doWork(connection -> {
            try (java.sql.Statement statement = connection.createStatement()) {
                statement.execute("SET search_path TO " + schemaName);
            }
        });
    }

    private void resetSchema() throws SQLException {
        Session session = entityManager.unwrap(Session.class);
        session.doWork(connection -> {
            try (java.sql.Statement statement = connection.createStatement()) {
                statement.execute("RESET search_path");
            }
        });
    }

    private Organization getOrganizationByUsername(String username) {
        // Ищем пользователя в схеме admin
        Session session = entityManager.unwrap(Session.class);
        session.doWork(connection -> {
            try (java.sql.Statement statement = connection.createStatement()) {
                statement.execute("SET search_path TO admin");
            }
        });

        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
            return user.getOrganization();
        } finally {
            // Возвращаемся к предыдущей схеме
            session.doWork(connection -> {
                try (java.sql.Statement statement = connection.createStatement()) {
                    statement.execute("RESET search_path");
                }
            });
        }
    }
}
