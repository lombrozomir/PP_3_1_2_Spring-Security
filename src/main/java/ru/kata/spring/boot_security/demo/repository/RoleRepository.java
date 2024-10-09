package ru.kata.spring.boot_security.demo.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Role findByRoleName(String roleName) {
        List<Role> roles = entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getResultList();

        return roles.isEmpty() ? null : roles.get(0);
    }

    public void save(Role role) {
        entityManager.persist(role);
    }

}
