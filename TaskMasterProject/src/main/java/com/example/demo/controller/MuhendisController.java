package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<MuhendisEntity> createMuhendis(@RequestBody MuhendisEntity muhendis) {
        MuhendisEntity createdMuhendis = muhendisService.saveMuhendis(muhendis);
        return ResponseEntity.ok(createdMuhendis);
    }

}
