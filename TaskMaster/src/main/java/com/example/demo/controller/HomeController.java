package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.TakimEntity;
import com.example.demo.service.TakimService;

@Controller
public class HomeController {

	 @GetMapping("/")
	    public String home() {
	        return "GirisSayfasi"; 
	    }
	
	 @GetMapping("/AnaSayfa.html")
	    public String AnaSayfa() {
	        return "AnaSayfa"; 
	    }
	 @GetMapping("/KayitGiris.html")
	    public String register() {
	        return "KayitGiris";
	    }
	
	 @GetMapping("/Login.html")
	    public String Login() {
	        return "Login";
	    }
	 
	 @GetMapping("/MuhendisKayit.html")
	    public String Muhendisregister() {
	        return "MuhendisKayit.html";
	    }
	 
	 @GetMapping("/TakimLideriKayit.html")
	    public String TakimLideriregister() {
	        return "TakimLideriKayit.html";
	    }
	 
	 @GetMapping("/StajerKayit.html")
	    public String Stajerregister() {
	        return "StajerKayit.html";
	    }
	 
	 @Autowired
	    private TakimService teamService;

	    @GetMapping("/TakimListesi.html")
	    public String showTeamList(Model model) {
	        List<TakimEntity> teams = teamService.findAllTakimlar();
	        model.addAttribute("teams", teams);
	        return "TakimListesi";
	    }


}
