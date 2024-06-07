package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.TakimEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.TakimService;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/TakimGirisi")
public class TakimController {

    @Autowired
    private TakimService takimService;

    @Autowired
    private UsersService usersService;
    
    @GetMapping("/TakimListesi.html")
    public String showTeamList(Model model) {
        List<TakimEntity> teams = takimService.findAllTakimlar();
        model.addAttribute("teams", teams);
        return "TakimListesi";
    }

    @PostMapping("/createTeam")
    public ResponseEntity<?> createTeam(@RequestParam("teamName") String teamName, @RequestParam("userId") Long userId, HttpSession session) {
        UsersEntity user = usersService.findById(userId);
        if (user == null) {
            return ResponseEntity.badRequest().body("Kullanıcı bulunamadı.");
        }

        if (!user.getRutbesi().equals("TakimLideri") || user.getTakimId() > 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Yalnızca takım liderleri ve takımsız kullanıcılar takım oluşturabilir.");
        }

        TakimEntity newTeam = new TakimEntity();
        newTeam.setTakimAdi(teamName);
        newTeam.setTakimLideriKullaniciId(user.getId());
        newTeam.setUyeSayisi(1);
        TakimEntity createdTeam = takimService.createTakim(newTeam);
        user.setTakimId(createdTeam.getTakimId());
        usersService.saveOrUpdateUser(user);

        session.setAttribute("user", user); // Session'da kullanıcıyı güncelle

        return ResponseEntity.ok(createdTeam);
    }

    
    @GetMapping("/getTeamNameByTeamId/{takimId}")
    public ResponseEntity<String> getTeamNameByTeamId(@PathVariable Long takimId) {
        Optional<TakimEntity> takim = takimService.findTakimByTakimId(takimId);
        if (takim.isPresent()) {
            return ResponseEntity.ok(takim.get().getTakimAdi());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Takım bilgisi bulunamadı");
        }
    }



}
