//package ru.kata.spring.boot_security.demo.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.models.User;
//import ru.kata.spring.boot_security.demo.services.UserService;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/users")
//public class UserController {
//
//    private static final String REDIRECT = "redirect:/users";
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/add")
//    public String showAddUserForm(Model model) {
//        model.addAttribute("user", new User());
//        return "addUser";
//    }
//
//    @GetMapping("/update")
//    public String showUpdateUserForm(@RequestParam Long id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "editUser";
//    }
//
//
//    @PostMapping("/create")
//    public String addUser(@ModelAttribute User user) {
//        userService.createUser(user);
//        return REDIRECT;
//    }
//
//    @GetMapping
//    public String listUsers(Model model) {
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("users", users);
//        return "userList";
//    }
//
//    @GetMapping("/read")
//    public String showUserInfo(@RequestParam Long id, Model model) {
//        User user = userService.getUserById(id);
//        if (user == null) {
//            return "redirect:/error";
//        }
//        model.addAttribute("user", user);
//        return "userInfo";
//    }
//
//
//    @PostMapping("/update")
//    public String updateUser(@RequestParam Long id, @RequestParam String name, @RequestParam String email, @RequestParam Byte age) {
//        User user = userService.getUserById(id);
//        if (user != null) {
//            user.setName(name);
//            user.setEmail(email);
//            user.setAge(age);
//            userService.updateUser(user);
//        }
//        return REDIRECT;
//    }
//
//    @PostMapping("/delete")
//    public String deleteUser(@RequestParam Long id) {
//        userService.deleteUser(id);
//        return REDIRECT;
//    }
//}stereotype
