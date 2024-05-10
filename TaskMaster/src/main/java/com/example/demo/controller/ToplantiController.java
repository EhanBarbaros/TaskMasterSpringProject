package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.MeetingRequest;
import com.example.demo.entity.Notification;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.NotificationService;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
@RequestMapping("/toplanti")
public class ToplantiController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/planla")
    public ResponseEntity<?> planMeeting(@RequestBody MeetingRequest request, HttpSession session) {
        UsersEntity currentUser = (UsersEntity) session.getAttribute("user");
        if (currentUser == null || !"TakimLideri".equals(currentUser.getRutbesi())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Yalnızca takım liderleri bu işlemi gerçekleştirebilir.");
        }

        List<UsersEntity> selectedUsers = usersService.findUsersByRoleAndTeam(request.getTakimId(), request.isSelectInterns(), request.isSelectEngineers());

        if (selectedUsers.isEmpty()) {
            return ResponseEntity.badRequest().body("Seçilen kullanıcılar bulunamadı veya takım ID'si yanlış.");
        }

        for (UsersEntity user : selectedUsers) {
            notificationService.createNotification(new Notification(
                "Toplantı Daveti",
                "Toplantı Zamanı: " + request.getMeetingTime(),
                currentUser.getUsername(),
                request.getTakimId(),
                user
            ));
        }

        return ResponseEntity.ok().body("Toplantı başarıyla planlandı ve bildirimler gönderildi.");
    }
}
