package com.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.repository.RoleRepository;
import com.blog.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    // Login View
    @GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/backend/dashboard";
        }
        return "frontend/login";
    }

    // Register View
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "frontend/register";
    }

    // Register User
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (!user.getPassword().equals(user.getRePassword())) {
            model.addAttribute("error", "Passwords do not match");
            return "frontend/register";
        }
        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            userRole = new Role(null, "USER");
            roleRepository.save(userRole);
        }
        user.setRoles(Collections.singleton(userRole));
        userService.saveUser(user);
        return "redirect:/login?success";
    }

    // Logout
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}