package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Notification;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.NotificationService;

import jakarta.servlet.http.HttpSession;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/bildirimler")
    public List<Notification> getNotifications(HttpSession session) {
        UsersEntity user = (UsersEntity) session.getAttribute("user");
        return notificationService.getNotificationsByUserId(user.getId());
    }
}