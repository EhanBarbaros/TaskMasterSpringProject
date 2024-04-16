package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
	UsersEntity findByUsernameAndPassword(String username, String password);
	Optional<UsersEntity> findByUsername(String username);
}
