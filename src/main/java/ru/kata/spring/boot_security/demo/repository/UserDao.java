package ru.kata.spring.boot_security.demo.repository;



import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {

    void createUser(User user);
    List<User> getAllUsers();
    void updateUser(User user);
    boolean deleteUser(long id);
    User getUserById(long id);

    User findByUserName(String username);
}
