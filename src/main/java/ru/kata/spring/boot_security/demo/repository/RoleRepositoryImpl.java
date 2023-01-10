package ru.kata.spring.boot_security.demo.repository;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;


@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> findAll() {
        return entityManager.createQuery("from Role", Role.class).getResultStream().collect(Collectors.toSet());
    }

}
