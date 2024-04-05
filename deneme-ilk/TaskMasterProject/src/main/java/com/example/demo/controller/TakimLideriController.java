package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<TakimLideriEntity> createTakimLideri(@RequestBody TakimLideriEntity takimlideri) {
    	TakimLideriEntity createdTakimLideri = takimLideriService.saveTakimLideri(takimlideri);
        return ResponseEntity.ok(createdTakimLideri);
    }

}
