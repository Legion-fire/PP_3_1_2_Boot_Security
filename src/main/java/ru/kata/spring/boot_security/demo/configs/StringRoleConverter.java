package ru.kata.spring.boot_security.demo.configs;

import org.springframework.core.convert.converter.Converter;
import ru.kata.spring.boot_security.demo.model.Role;

public class StringRoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String id) {
        Role role = new Role();
        role.setId(Long.valueOf(id));
        return role;
    }
}


