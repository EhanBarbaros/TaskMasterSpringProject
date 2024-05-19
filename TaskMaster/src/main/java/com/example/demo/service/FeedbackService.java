package com.example.demo.service;

import com.example.demo.dto.FeedbackDTO;
import com.example.demo.entity.FeedbackEntity;
import com.example.demo.entity.TaskEntity;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private TaskRepository taskRepository;

    public FeedbackEntity saveFeedback(FeedbackDTO feedbackDTO) {
        if (feedbackDTO.getTaskId() == null) {
            throw new IllegalArgumentException("Task ID must not be null");
        }

        TaskEntity task = taskRepository.findById(feedbackDTO.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setTaskId(feedbackDTO.getTaskId());
        feedback.setFeedbackContent(feedbackDTO.getFeedbackContent());
        feedback.setSenderId(task.getTaskAlanKullaniciId());
        feedback.setReceiverId(task.getTaskVerenKullaniciId());
        feedback.setDateTime(LocalDateTime.now());
        feedback.setOnaylandi(false); // Default olarak false ayarlanıyor

        return feedbackRepository.save(feedback);
    }

    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public List<FeedbackEntity> getFeedbackByTaskId(Long taskId) {
        return feedbackRepository.findByTaskId(taskId);
    }

    public List<FeedbackEntity> getPendingFeedbacksByReceiverId(Long receiverId) {
        return feedbackRepository.findByReceiverIdAndOnaylandiFalse(receiverId);
    }

    public FeedbackEntity approveFeedback(Long feedbackId) {
        FeedbackEntity feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        feedback.setOnaylandi(true);
        return feedbackRepository.save(feedback);
    }
    


}
