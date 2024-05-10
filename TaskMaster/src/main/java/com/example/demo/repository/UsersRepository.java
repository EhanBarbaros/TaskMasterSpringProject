package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByUsernameAndPassword(String username, String password);
    Optional<UsersEntity> findByUsername(String username);
    
    @Query("SELECT u FROM UsersEntity u WHERE u.TakimId = :takimId")
    List<UsersEntity> findByTakimIdCustom(@Param("takimId") Long takimId);

    @Query("SELECT u FROM UsersEntity u WHERE u.username IN :usernames AND u.TakimId = :takimId")
    List<UsersEntity> findByUsernamesAndTakimId(@Param("usernames") List<String> usernames, @Param("takimId") Long takimId);

    @Query("SELECT u FROM UsersEntity u WHERE u.TakimId = :takimId AND u.rutbesi IN :roles")
    List<UsersEntity> findByTakimIdAndRoles(@Param("takimId") Long takimId, @Param("roles") List<String> roles);
}
