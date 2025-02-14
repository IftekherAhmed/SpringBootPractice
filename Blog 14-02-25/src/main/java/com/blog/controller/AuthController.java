package com.blog.controller;

import lombok.RequiredArgsConstructor;
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
    private UserService userService;
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        }
        return "frontend/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "frontend/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            userRole = new Role(null, "USER");
            roleRepository.save(userRole);
        }
        user.setRoles(Collections.singleton(userRole));
        userService.saveUser(user);
        return "redirect:/login?success";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}