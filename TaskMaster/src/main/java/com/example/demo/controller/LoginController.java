package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        UsersEntity user = usersService.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("user", user);  // Kullanıcıyı session'a kaydet
            return "redirect:/TakimGirisi";
        } else {
            model.addAttribute("error", "Kullanıcı adı veya şifre hatalı!");
            return "login";
        }
    }
    
    



}
