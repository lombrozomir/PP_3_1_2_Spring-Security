package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String redirectToPanel() {
        return "redirect:/admin/panel";
    }

//    @GetMapping("/panel")
//    public String allUsersShow(Model model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "userList";
//    }

    @GetMapping("/panel")
    public String allUsersShow(Model model) {

        List<User> allUsers = userService.getAllUsers();

        List<User> filteredUsers = allUsers.stream()
                .filter(user -> !user.getRoles().contains("ROLE_ADMIN"))
                .collect(Collectors.toList());

        model.addAttribute("users", filteredUsers);

        return "userList";
    }

    @GetMapping("/create")
    public String userCreateShow(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/create")
    public String userCreate(@ModelAttribute User user, @RequestParam(value = "roles", required = false) List<String> roles) {
        userService.createUserWithRole(user,roles);
        return "redirect:/admin/panel";
    }

    @GetMapping("/read")
    public String userRead(@RequestParam Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/error";
        }
        model.addAttribute("user", user);
        return "userInfo";
    }

    @GetMapping("/update")
    public String updateUserShow(@RequestParam Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/error";
        }
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam Long id, @RequestParam String name, @RequestParam Byte age) {


        User existingUser = userService.getUserById(id);
        if (existingUser != null) {

            existingUser.setName(name);
            existingUser.setAge(age);

            userService.updateUser(existingUser);
        }
        return "redirect:/admin/panel";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/panel";
    }

//    private void authenticateUser(User user) {
//        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
//        UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//    }

}
