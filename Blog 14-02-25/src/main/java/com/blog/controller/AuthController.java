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
    private UserService userService; // Inject UserService

    @Autowired
    private RoleRepository roleRepository; // Inject RoleRepository

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, @RequestParam(value = "error", required = false) String error, Model model) {
        HttpSession session = request.getSession(false); // Get existing session if present
        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/backend/dashboard"; // Redirect to dashboard if user is already logged in
        }
        if (error != null) {
            model.addAttribute("error", "Invalid username or password"); // Add error message to the model
        }
        return "frontend/login"; // Return login page
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User()); // Add new user to the model
        return "frontend/register"; // Return register page
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (!user.getPassword().equals(user.getRePassword())) {
            model.addAttribute("error", "Passwords do not match"); // Add error message to the model
            return "frontend/register"; // Return register page
        }
        Role userRole = roleRepository.findByName("USER"); // Find USER role
        if (userRole == null) {
            userRole = new Role(null, "USER"); // Create USER role if not found
            roleRepository.save(userRole); // Save USER role
        }
        user.setRoles(Collections.singleton(userRole)); // Set USER role to the user
        userService.saveUser(user); // Save user
        return "redirect:/login?success"; // Redirect to login page with success message
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Get existing session if present
        if (session != null) {
            session.invalidate(); // Invalidate session
        }
        return "redirect:/login"; // Redirect to login page
    }
}