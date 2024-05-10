package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.TakimEntity;

public interface TakimRepository extends JpaRepository<TakimEntity, Long> {
    boolean existsByTakimAdi(String takimAdi);
    Optional<TakimEntity> findById(Long id);
    
    Optional<TakimEntity> findByTakimId(Long takimId);
}
