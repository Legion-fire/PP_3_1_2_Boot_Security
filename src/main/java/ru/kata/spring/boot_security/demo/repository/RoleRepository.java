package ru.kata.spring.boot_security.demo.repository;


import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

public interface RoleRepository {
    Set<Role> findAll();

}
