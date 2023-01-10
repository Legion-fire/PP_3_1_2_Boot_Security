package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.*;


@Component
public class Init {

    private final PasswordEncoder encoder;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(PasswordEncoder encoder, UserService userService, RoleService roleService) {
        this.encoder = encoder;
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
        public void createTable () {
        if (userService.findAll().isEmpty()){
        Role admin = new Role( "ROLE_ADMIN", 1L);
        Role user = new Role("ROLE_USER", 2L);
        roleService.add(admin);
        roleService.add(user);
        Set<Role> setRole = new HashSet<>();
        setRole.add(admin);
        User newAdmin = new User("Михаил", "Янаров", 28,
                "Legion-fire@yandex.ru", "$2a$12$nNj251hCDwafhk/uGw2Wtehm7whW7QWOx6rdmxLKliLl5X8QbUSC.", setRole);
        userService.save(newAdmin);
        }
    }
}
