package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.TakimLideriEntity;
import com.example.demo.service.TakimLideriService;

@RestController
@RequestMapping("/takimliderleri")
public class TakimLideriController {

    @Autowired
    private TakimLideriService takimLideriService;

    @PostMapping
    public ResponseEntity<?> TakimLideriEntity(@RequestBody TakimLideriEntity tlideri) {
        if (tlideri.getKullanici() == null) {
            return ResponseEntity.badRequest().body("Kullanıcı bilgisi eksik.");
        }
        try {
        	TakimLideriEntity createdTakimLideri = takimLideriService.saveTakimLideri(tlideri);
            return ResponseEntity.ok(createdTakimLideri);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bir hata oluştu: " + e.getMessage());
        }
    }

}
