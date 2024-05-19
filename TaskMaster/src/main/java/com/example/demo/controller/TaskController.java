package com.example.demo.controller;

import com.example.demo.dto.TaskDTO;
import com.example.demo.entity.Notification;
import com.example.demo.entity.TaskEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.NotificationService;
import com.example.demo.service.TaskService;
import com.example.demo.service.UsersService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/assign")
    public String assignTask(
            @RequestParam("title") String title,
            @RequestParam("icerik") String content,
            @RequestParam("assignedToUsername") String assignedToUsername,
            HttpSession session,
            Model model) {
        UsersEntity currentUser = (UsersEntity) session.getAttribute("user");

        if (currentUser == null) {
            return "redirect:/login";
        }

        UsersEntity assignedToUser = usersService.findByUsername(assignedToUsername).orElse(null);
        if (assignedToUser == null) {
            model.addAttribute("error", "Kullanıcı bulunamadı.");
            return "GorevAta";
        }

        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setIcerik(content);
        task.setTaskAlanKullaniciId(assignedToUser.getId());
        task.setTaskVerenKullaniciId(currentUser.getId());
        task.setTamamlandi(false);

        taskService.saveTask(task);

        notificationService.createNotification(new Notification(
                "Yeni Görev",
                "Yeni bir görev atanmıştır: " + title,
                currentUser.getUsername(),
                currentUser.getTakimId(),
                assignedToUser
        ));

        model.addAttribute("message", "Görev başarıyla atandı.");
        return "GorevAta";
    }
    
    @GetMapping("/Gorevlerim")
    public String showTasksPage(HttpSession session, Model model) {
        UsersEntity currentUser = (UsersEntity) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        return "Gorevlerim";
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<TaskDTO>> getActiveTasks(HttpSession session) {
        UsersEntity currentUser = (UsersEntity) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseEntity.status(401).body(null);
        }

        Long userId = currentUser.getId();
        List<TaskDTO> activeTasks = taskService.getActiveTasksForUser(userId);
        return ResponseEntity.ok(activeTasks);
    }
   
    
}
