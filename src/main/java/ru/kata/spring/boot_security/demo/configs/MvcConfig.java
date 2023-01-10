package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringRoleConverter());
    }
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/index.html");
        registry.addViewController("/admin.html");
        registry.addViewController("/edit.html");
        registry.addViewController("/new.html");
        registry.addViewController("/auth.html");
    }
}
