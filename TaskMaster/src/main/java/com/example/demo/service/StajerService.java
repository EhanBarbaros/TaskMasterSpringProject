package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.StajerEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.StajerRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class StajerService {

    @Autowired
    private StajerRepository stajerRepository;

    @Autowired
    private UsersRepository usersRepository;

    
    public StajerEntity saveStajer(StajerEntity stajer) {
        UsersEntity user = stajer.getKullanici();
        user.setRutbesi("Stajer"); 
        
        UsersEntity savedUser = usersRepository.save(user);
        stajer.setKullanici(savedUser); 
        
        return stajerRepository.save(stajer);
    }
}
