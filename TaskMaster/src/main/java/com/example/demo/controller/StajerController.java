package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<StajerEntity> createStajer(@RequestBody StajerEntity stajer) {
    	StajerEntity createdStajer = stajerService.saveStajer(stajer);
        return ResponseEntity.ok(createdStajer);
    }

}
