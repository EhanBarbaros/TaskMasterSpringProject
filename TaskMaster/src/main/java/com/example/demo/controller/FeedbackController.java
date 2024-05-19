package com.example.demo.controller;

import com.example.demo.dto.FeedbackDTO;
import com.example.demo.entity.FeedbackEntity;
import com.example.demo.service.FeedbackService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

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
}
