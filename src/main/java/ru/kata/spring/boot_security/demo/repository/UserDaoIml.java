package ru.kata.spring.boot_security.demo.repository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoIml implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void updateUser(User user) {
        if (user != null) {
            entityManager.merge(user);
        }
    }

    @Override
    public boolean deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {

            user.getRoles().clear();

            entityManager.remove(user);
            return true;
        } else {
            return false;
        }
    }



    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUserName(String hui) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :hui", User.class)
                .setParameter("hui", hui).getSingleResult();
    }
}
