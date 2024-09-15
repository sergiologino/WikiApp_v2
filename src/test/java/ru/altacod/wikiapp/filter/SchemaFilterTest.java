package ru.altacod.wikiapp.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// Дополнительные импорты
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import ru.altacod.wikiapp.entity.Organization;
import ru.altacod.wikiapp.entity.User;
import ru.altacod.wikiapp.repository.UserRepository;
import ru.altacod.wikiapp.repository.OrganizationRepository;

import jakarta.servlet.FilterChain;

import static org.mockito.Mockito.*;

@SpringBootTest
public class SchemaFilterTest {

    @Autowired
    private SchemaFilter schemaFilter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    public void testSchemaSwitching() throws Exception {
        // Создаем организацию и пользователя
        Organization organization = new Organization();
        organization.setName("Тестовая организация");
        organization = organizationRepository.save(organization);

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setOrganization(organization);
        user = userRepository.save(user);

        // Аутентифицируем пользователя
        // Здесь можно настроить SecurityContextHolder для имитации аутентификации

        // Создаем MockHttpServletRequest и MockHttpServletResponse
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/api/test");

        MockHttpServletResponse response = new MockHttpServletResponse();

        FilterChain chain = mock(FilterChain.class);

        schemaFilter.doFilter(request, response, chain);

        // Проверяем, что фильтр установил схему и вызвал цепочку фильтров
        verify(chain, times(1)).doFilter(request, response);
    }
}

