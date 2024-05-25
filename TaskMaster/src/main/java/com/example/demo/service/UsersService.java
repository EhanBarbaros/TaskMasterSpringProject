package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TakimEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.TakimRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private TakimRepository teamRepository;

    public UsersEntity saveOrUpdateUser(UsersEntity user) {
        return usersRepository.save(user);
    }

    public UsersEntity findByUsernameAndPassword(String username, String password) {
        return usersRepository.findByUsernameAndPassword(username, password);
    }

    public Optional<UsersEntity> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
    
    public String getTeamNameById(Long teamId) {
        TakimEntity team = teamRepository.findById(teamId).orElse(null);
        return (team != null) ? team.getTakimAdi() : "Takım Bulunamadı";
    }

    public UsersEntity findById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public List<UsersEntity> findByTakimId(Long takimId) {
        return usersRepository.findByTakimIdCustom(takimId);
    }

    public String getBadgeClassByPosition(String rutbesi) {
        switch (rutbesi) {
            case "TakimLideri":
                return "badge-danger";
            case "Mühendis":
                return "badge-warning";
            case "Stajer":
                return "badge-info";
            default:
                return "badge-secondary";
        }
    }
    
    public List<UsersEntity> findUsersByRoleAndTeam(Long takimId, boolean selectInterns, boolean selectEngineers) {
        List<String> roles = new ArrayList<>();
        if (selectInterns) roles.add("Stajer");
        if (selectEngineers) roles.add("Mühendis");

        return usersRepository.findByTakimIdAndRoles(takimId, roles);
    }
    
    public int getCompletedTaskCount(Long userId) {
        return taskRepository.countByTaskAlanKullaniciIdAndTamamlandiTrue(userId);
    }

    
    
    	
}
