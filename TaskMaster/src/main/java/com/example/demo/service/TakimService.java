package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.TakimEntity;
import com.example.demo.repository.TakimRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TakimService {

	 @Autowired
	    private TakimRepository takimRepository;

	    public List<TakimEntity> findAllTakimlar() {
	        return takimRepository.findAll();
	    }

	    public Optional<TakimEntity> findTakimById(Long id) {
	        return takimRepository.findById(id);
	    }

	    public TakimEntity createTakim(TakimEntity takim) {
	        if (takimRepository.existsByTakimAdi(takim.getTakimAdi())) {
	            throw new IllegalStateException("Aynı isimde bir takım zaten var!");
	        }
	        takim.setUyeSayisi(1);
	        return takimRepository.save(takim);
	    }
    
}
