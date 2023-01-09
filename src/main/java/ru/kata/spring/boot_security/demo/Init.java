package ru.kata.spring.boot_security.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


@Component
public class Init {

    private final PasswordEncoder encoder;
        private  UserDAO userService;
        private RoleDAO roleService;

    @Autowired
    public Init(PasswordEncoder encoder, UserDAO userService, RoleDAO roleService) {
        this.encoder = encoder;
        this.userService = userService;
            this.roleService = roleService;
        }


    @PostConstruct
        public void createTable () {
        if (roleService.findAll().isEmpty()) {
        Role admin = new Role(1L, "ROLE_ADMIN");
        Role user = new Role(2L, "ROLE_USER");
        final User user1 = new User();
        user1.setName("john");
        user1.setEmail("Legion-fire@yandex.ru");
        user1.setPassword(encoder.encode("123"));
        user1.setRoles(user1.getRoles());
        userService.save(user1);
        }
    }
}
