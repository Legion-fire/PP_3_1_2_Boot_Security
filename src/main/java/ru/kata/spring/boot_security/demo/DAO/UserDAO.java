package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByEmail(String email);
    void delete(User user);
}
