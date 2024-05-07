package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.StajerEntity;
import com.example.demo.service.StajerService;

@RestController
@RequestMapping("/stajerler")
public class StajerController {

    @Autowired
    private StajerService stajerService;

    @PostMapping
    public ResponseEntity<?> StajerEntity(@RequestBody StajerEntity stajer) {
        if (stajer.getKullanici() == null) {
            return ResponseEntity.badRequest().body("Kullanıcı bilgisi eksik.");
        }
        try {
        	StajerEntity createdStajer = stajerService.saveStajer(stajer);
            return ResponseEntity.ok(createdStajer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bir hata oluştu: " + e.getMessage());
        }
    }

}
