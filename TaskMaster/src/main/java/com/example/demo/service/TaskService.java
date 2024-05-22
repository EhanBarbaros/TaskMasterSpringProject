package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FeedbackDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.entity.FeedbackEntity;
import com.example.demo.entity.TaskEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UsersRepository;


@Service
public class TaskService {
    
	@Autowired
    private TaskRepository taskRepository;
	@Autowired
    private UsersRepository usersRepository;
	
	@Autowired
    private FeedbackRepository feedbackRepository;
	
    

    public void saveTask(TaskEntity task) {
        taskRepository.save(task);
    }
    
    public List<TaskDTO> getActiveTasksForUser(Long userId) {
        List<TaskEntity> tasks = taskRepository.findByTaskAlanKullaniciIdAndTamamlandiFalse(userId);

        return tasks.stream().map(task -> {
            TaskDTO dto = new TaskDTO();
            dto.setTaskId(task.getTaskId());
            dto.setTitle(task.getTitle());
            dto.setIcerik(task.getIcerik());
            dto.setCreateDate(task.getCreateDate());

            UsersEntity user = usersRepository.findById(task.getTaskVerenKullaniciId()).orElse(null);
            if (user != null) {
                dto.setTaskVerenKullaniciUsername(user.getUsername());
            }

            return dto;
        }).collect(Collectors.toList());
    }
    
    public List<TaskDTO> getCompletedTasksForUser(Long userId) {
        List<TaskEntity> tasks = taskRepository.findByTaskAlanKullaniciIdAndTamamlandiTrue(userId);

        return tasks.stream().map(task -> {
            TaskDTO dto = new TaskDTO();
            dto.setTaskId(task.getTaskId());
            dto.setTitle(task.getTitle());
            dto.setIcerik(task.getIcerik());
            dto.setCreateDate(task.getCreateDate());

            UsersEntity user = usersRepository.findById(task.getTaskVerenKullaniciId()).orElse(null);
            if (user != null) {
                dto.setTaskVerenKullaniciUsername(user.getUsername());
            }

            return dto;
        }).collect(Collectors.toList());
    }
    
    public List<FeedbackDTO> getPendingFeedbacksForUser(Long userId) {
        List<FeedbackEntity> feedbacks = feedbackRepository.findByReceiverIdAndOnaylandiFalse(userId);

        return feedbacks.stream().map(feedback -> {
            FeedbackDTO dto = new FeedbackDTO();
            dto.setId(feedback.getId());
            dto.setTaskId(feedback.getTaskId());
            dto.setFeedbackContent(feedback.getFeedbackContent());
            dto.setDateTime(feedback.getDateTime());

            UsersEntity sender = usersRepository.findById(feedback.getSenderId()).orElse(null);
            if (sender != null) {
                dto.setSenderUsername(sender.getUsername());
            }

            TaskEntity task = taskRepository.findById(feedback.getTaskId()).orElse(null);
            if (task != null) {
                dto.setTaskTitle(task.getTitle());
                dto.setTaskContent(task.getIcerik());
            }

            return dto;
        }).collect(Collectors.toList());
    }
    
    
    public void updateTaskAndFeedbackStatus(Long taskId) {
        Optional<TaskEntity> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent()) {
            TaskEntity task = taskOpt.get();
            task.setTamamlandi(true);
            taskRepository.save(task);

            List<FeedbackEntity> feedbacks = feedbackRepository.findByTaskId(taskId);
            for (FeedbackEntity feedback : feedbacks) {
                feedback.setOnaylandi(true);
                feedbackRepository.save(feedback);
            }
        }
    }
    
}
