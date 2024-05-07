package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/kullanicilar")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<UsersEntity> createUser(@RequestBody UsersEntity user) {
        UsersEntity createdUser = usersService.saveOrUpdateUser(user);
        return ResponseEntity.ok(createdUser);
    }
    
    @GetMapping("/user-details")
    public String showUserDetails(Model model, HttpSession session) {
        UsersEntity user = (UsersEntity) session.getAttribute("user");
        if (user != null) {
            String badgeClass = usersService.getBadgeClassByPosition(user.getRutbesi());
            model.addAttribute("user", user);
            model.addAttribute("badgeClass", badgeClass);
        }
        return "AnaSayfa";
    }
    
}
