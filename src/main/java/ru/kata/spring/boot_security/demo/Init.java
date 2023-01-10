package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;


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
        if (roleService.findAll().isEmpty()) {
        Role admin = new Role("ROLE_ADMIN", 56L);
        Role user = new Role("ROLE_USER", 57L);
        final User user1 = new User();
        user1.setName("Мишка");
        user1.setEmail("Zara@yandex.ru");
        user1.setPassword(encoder.encode("123"));
        user1.setRoles(user1.getRoles());
        userService.save(user1);
        }
    }
}
