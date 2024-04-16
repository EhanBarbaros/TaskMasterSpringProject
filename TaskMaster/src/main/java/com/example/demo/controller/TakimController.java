package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.TakimEntity;
import com.example.demo.service.TakimService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/TakimGirisi")
public class TakimController {

    @Autowired
    private TakimService takimService;

    @GetMapping
    public ResponseEntity<List<TakimEntity>> getAllTakimlar() {
        List<TakimEntity> takimlar = takimService.findAllTakimlar();
        return ResponseEntity.ok(takimlar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TakimEntity>> getTakimById(@PathVariable Long id) {
        Optional<TakimEntity> takim = takimService.findTakimById(id);
        if (takim != null) {
            return ResponseEntity.ok(takim);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/createTeam")
    public ModelAndView createTeam(@RequestParam("teamName") String teamName) {
        TakimEntity newTakim = new TakimEntity();
        newTakim.setTakimAdi(teamName);
        newTakim.setUyeSayisi(1); // Assume initially there is only the team leader

        try {
            TakimEntity createdTakim = takimService.createTakim(newTakim);
            return new ModelAndView("redirect:/teamPage/" + createdTakim.getTakimId());
        } catch (IllegalStateException e) {
            ModelAndView modelAndView = new ModelAndView("takimListesi");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }
}
