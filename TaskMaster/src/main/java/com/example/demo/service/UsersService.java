package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public UsersEntity saveOrUpdateUser(UsersEntity user) {
        return usersRepository.save(user);
    }

    public UsersEntity findByUsernameAndPassword(String username, String password) {
        return usersRepository.findByUsernameAndPassword(username, password);
    }

    public Optional<UsersEntity> findByUsername(String username) {
        return usersRepository.findByUsername(username);
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
}
