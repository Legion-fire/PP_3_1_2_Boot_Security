package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
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
    public String addNewUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roleSetList",roleService.findAll());
        return "new";
    }

    @PostMapping("/newAddUserAdmin")
    public String saveNewUser(@ModelAttribute("user") User user,
                              @RequestParam("roleSetList") ArrayList<Long> roles) {
        user.setRoles(roleService.findByIdRoles(roles));
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String  deleteUser(@RequestParam( "id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roleSetList",roleService.findAll());
        return "edit";
    }

    @PostMapping("/{id}")
    public String userSaveEdit(@Valid User user,
                               @RequestParam("roleSetList") ArrayList<Long>roles) {
        user.setRoles(roleService.findByIdRoles(roles));
        userService.save(user);
        return "redirect:/admin";
    }

}
