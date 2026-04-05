package com.ltc.controller;

import com.ltc.model.User;
import com.ltc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userid,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        
        Optional<User> userOpt = userRepository.findByEmail(userid);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // In production, use password encoder
            if (user.getPassword().equals(password)) {
                session.setAttribute("userId", user.getId());
                session.setAttribute("userRole", user.getRole());
                session.setAttribute("userName", user.getName());
                
                switch (user.getRole()) {
                    case "hospital":
                        return "redirect:/hospital/dashboard";
                    case "ltc":
                        return "redirect:/ltc/dashboard";
                    case "admin":
                        return "redirect:/login";
                    default:
                        return "redirect:/login";
                }
            }
        }
        
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}