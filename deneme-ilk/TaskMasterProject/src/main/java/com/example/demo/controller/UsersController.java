package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.UsersService;

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
}
