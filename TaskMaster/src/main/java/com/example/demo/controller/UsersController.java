package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserTeamRequest;
import com.example.demo.entity.Notification;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.NotificationService;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/kullanicilar")
public class UsersController {

    @Autowired
    private UsersService usersService;
    
    @Autowired
    private NotificationService notificationService;

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
    
    @GetMapping("/team-users")
    public ResponseEntity<List<UsersEntity>> getUsersByTeamId(@RequestParam Long takimId) {
        List<UsersEntity> users = usersService.findByTakimId(takimId);
        return ResponseEntity.ok(users);
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addUserToTeam(@RequestBody UserTeamRequest request, HttpSession session) {
        UsersEntity currentUser = (UsersEntity) session.getAttribute("user");
        if (currentUser == null || !"TakimLideri".equals(currentUser.getRutbesi())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Yalnızca takım liderleri bu işlemi gerçekleştirebilir.");
        }

        UsersEntity user = usersService.findByUsername(request.getUserName()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("Kullanıcı bulunamadı.");
        }

        if (user.getTakimId() == 0) {
            user.setTakimId(request.getTakimId());
            usersService.saveOrUpdateUser(user);
            notificationService.createNotification(new Notification(
                "Takıma Alındınız",
                "Takımınıza hoşgeldiniz!",
                currentUser.getUsername(),
                request.getTakimId(),
                user
            ));
            return ResponseEntity.ok().body("Kullanıcı başarıyla takıma eklendi.");
        } else {
            return ResponseEntity.badRequest().body("Kullanıcı zaten bir takıma kayıtlı.");
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeUserFromTeam(@RequestBody UserTeamRequest request, HttpSession session) {
        UsersEntity currentUser = (UsersEntity) session.getAttribute("user");
        if (currentUser == null || !"TakimLideri".equals(currentUser.getRutbesi())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Yalnızca takım liderleri bu işlemi gerçekleştirebilir.");
        }

        UsersEntity user = usersService.findByUsername(request.getUserName()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("Kullanıcı bulunamadı.");
        }

        if (user.getTakimId().equals(request.getTakimId())) {
            user.setTakimId(0L);
            usersService.saveOrUpdateUser(user);
            notificationService.createNotification(new Notification(
                "Takımdan Çıkarıldınız",
                "Artık bu takımın üyesi değilsiniz.",
                currentUser.getUsername(),
                request.getTakimId(),
                user
            ));
            return ResponseEntity.ok().body("Kullanıcı başarıyla takımdan çıkarıldı.");
        } else {
            return ResponseEntity.badRequest().body("Kullanıcı bu takımın üyesi değil.");
        }
    }
}
