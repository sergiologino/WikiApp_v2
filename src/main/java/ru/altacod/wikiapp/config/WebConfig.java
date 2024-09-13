package ru.altacod.wikiapp.config;

import ru.altacod.wikiapp.filter.SchemaFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SchemaFilter schemaFilter;

    @Bean
    public FilterRegistrationBean<SchemaFilter> schemaFilterRegistration() {
        FilterRegistrationBean<SchemaFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(schemaFilter);
        registration.addUrlPatterns("/*");
        registration.setName("schemaFilter");
        registration.setOrder(1);
        return registration;
    }
}
