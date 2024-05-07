package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.TakimLideriEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.TakimLideriRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class TakimLideriService {

    @Autowired
    private TakimLideriRepository takimLideriRepository;

    @Autowired
    private UsersRepository usersRepository;

    
    public TakimLideriEntity saveTakimLideri(TakimLideriEntity takimlideri) {
        UsersEntity user = takimlideri.getKullanici();
        user.setRutbesi("TakimLideri"); 
        
        UsersEntity savedUser = usersRepository.save(user);
        takimlideri.setKullanici(savedUser); 
        
        return takimLideriRepository.save(takimlideri);
        }

}
