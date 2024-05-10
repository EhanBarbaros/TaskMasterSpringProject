package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.MuhendisEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.MuhendisRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class MuhendisService {

    @Autowired
    private MuhendisRepository muhendisRepository;
    
    @Autowired
    private UsersRepository usersRepository;

    
    public MuhendisEntity saveMuhendis(MuhendisEntity muhendis) {
        UsersEntity user = muhendis.getKullanici();
        user.setRutbesi("Mühendis"); 
        
        UsersEntity savedUser = usersRepository.save(user);
        muhendis.setKullanici(savedUser); 
        
        return muhendisRepository.save(muhendis);
    }

}

