package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserDAO userService;
    private final RoleDAO roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserDAO userService, RoleDAO roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getUser(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "admin";
    }

    @GetMapping("/newAddUserAdmin")
    public String addNewUser(Model model) {
      User user = new User();
      model.addAttribute("user", user);
      List<Role> roles = roleService.findAll();
      model.addAttribute("roleList", roles);
      return "new";
    }

    @PostMapping("/newAddUserAdmin")
    public String saveNewUser(
            @ModelAttribute("user") User user
            ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {

        model.addAttribute("user", userService.findById(id).get());
        model.addAttribute("roleList",roleService.findAll());
        return "edit";
    }

    @PostMapping("/{id}")
    public String userSaveEdit(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }
}
