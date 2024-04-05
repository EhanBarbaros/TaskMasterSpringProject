package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public UsersEntity saveOrUpdateUser(UsersEntity user) {
        return usersRepository.save(user);
    }


}
