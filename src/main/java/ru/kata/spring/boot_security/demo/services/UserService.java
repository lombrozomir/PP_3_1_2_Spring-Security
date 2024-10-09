package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UserService {

    void createUser(User user);

    void createUserWithRole(User user, List<String> roles);

    List<User> getAllUsers();

    void updateUser(User user);

    boolean deleteUser(long id);

    User getUserById(long id);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
