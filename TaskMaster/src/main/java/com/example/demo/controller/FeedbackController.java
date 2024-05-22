package com.example.demo.controller;

import com.example.demo.dto.FeedbackDTO;
import com.example.demo.entity.FeedbackEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.FeedbackService;
import com.example.demo.service.TaskService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    
    @Autowired
    private TaskService taskService;

    @PostMapping("/submit")
    public ResponseEntity<FeedbackEntity> submitFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        FeedbackEntity savedFeedback = feedbackService.saveFeedback(feedbackDTO);
        return ResponseEntity.ok(savedFeedback);
    }

    @GetMapping
    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/task/{taskId}")
    public List<FeedbackEntity> getFeedbackByTaskId(@PathVariable Long taskId) {
        return feedbackService.getFeedbackByTaskId(taskId);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<FeedbackDTO>> getPendingFeedbacks(HttpSession session) {
        UsersEntity currentUser = (UsersEntity) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseEntity.status(401).body(null);
        }

        Long userId = currentUser.getId();
        List<FeedbackDTO> pendingFeedbacks = taskService.getPendingFeedbacksForUser(userId);
        return ResponseEntity.ok(pendingFeedbacks);
    }

    @PostMapping("/approve/{feedbackId}")
    public ResponseEntity<FeedbackEntity> approveFeedback(@PathVariable Long feedbackId) {
        FeedbackEntity approvedFeedback = feedbackService.approveFeedback(feedbackId);
        
        // Task ID'yi feedback entity'den alarak updateTaskAndFeedbackStatus metodunu çağırıyoruz
        taskService.updateTaskAndFeedbackStatus(approvedFeedback.getTaskId());
        
        return ResponseEntity.ok(approvedFeedback);
    }
    
}
