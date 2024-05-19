package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.entity.TaskEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UsersRepository;


@Service
public class TaskService {
    
	@Autowired
    private TaskRepository taskRepository;
	@Autowired
    private UsersRepository usersRepository;


	public Optional<TaskEntity> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }
    

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
    
}
