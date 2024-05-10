package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.MuhendisEntity;
import com.example.demo.service.MuhendisService;

@RestController
@RequestMapping("/muhendisler")
public class MuhendisController {

    @Autowired
    private MuhendisService muhendisService;

    @PostMapping
    public ResponseEntity<?> createMuhendis(@RequestBody MuhendisEntity muhendis) {
        if (muhendis.getKullanici() == null) {
            return ResponseEntity.badRequest().body("Kullanıcı bilgisi eksik.");
        }
        try {
            MuhendisEntity createdMuhendis = muhendisService.saveMuhendis(muhendis);
            return ResponseEntity.ok(createdMuhendis);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bir hata oluştu: " + e.getMessage());
        }
    }

    

}
