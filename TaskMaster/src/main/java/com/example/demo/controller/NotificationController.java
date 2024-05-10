package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Notification;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.NotificationService;

import jakarta.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/bildirimler")
public class NotificationController {



    @Autowired
    private NotificationService notificationService;



    @GetMapping
    public List<Notification> getNotifications(HttpSession session) {
        Long userId = ((UsersEntity) session.getAttribute("user")).getId();
        return notificationService.getNotificationsByUserId(userId);
    }

    @PostMapping("/markAsRead")
    public void markAsRead(@RequestParam Long notificationId) {
        notificationService.markAsRead(notificationId);
    }
}
