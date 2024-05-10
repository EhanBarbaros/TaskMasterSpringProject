package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.TakimEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.service.TakimService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private TakimService takimService ;

	 @GetMapping("/")
	    public String home() {
	        return "GirisSayfasi"; 
	    }

	 
	 @GetMapping("/AnaSayfa")
		public String showTeamHomePage(@RequestParam(required = false) Long takimId, Model model, HttpSession session) {
		    UsersEntity user = getCurrentUser(session);
		    if (user != null) {
		        model.addAttribute("user", user); // Kullanıcı bilgisini modele ekleyin
		        model.addAttribute("isTeamLeader", "TakimLideri".equals(user.getRutbesi()));

		        if (user.getTakimId() != null && user.getTakimId() > 0) {
		            Optional<TakimEntity> team = takimService.findTakimByTakimId(user.getTakimId());
		            if (team.isPresent()) {
		                model.addAttribute("teamName", team.get().getTakimAdi());
		                return "AnaSayfa"; // Kullanıcı takıma kayıtlıysa ana sayfayı göster
		            } else {
		                model.addAttribute("error", "Takım bilgisi bulunamadı.");
		            }
		        } else {
		            model.addAttribute("error", "Kullanıcı bir takıma kayıtlı değil.");
		        }
		    } else {
		        return "redirect:/login"; // Kullanıcı giriş yapmamışsa login sayfasına yönlendir
		    }
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
	 
	 
	 @GetMapping("/TakimGirisi")
	 public String TakimGirisi(Model model, HttpSession session) {
	     UsersEntity user = getCurrentUser(session);
	     if (user != null && user.getTakimId() > 0) {  // takimId için sıfır kontrolü
	         Optional<TakimEntity> team = takimService.findTakimByTakimId(user.getTakimId());
	         if (team.isPresent()) {
	             model.addAttribute("teamName", team.get().getTakimAdi());
	         } else {
	             model.addAttribute("error", "Takım bilgisi bulunamadı.");
	         }
	     } else {
	         model.addAttribute("error", "Kullanıcı bir takıma kayıtlı değil.");
	     }
	     model.addAttribute("user", user); // Her durumda user'ı modele ekleyin
	     return "TakimGirisi";
	 }



	 public UsersEntity getCurrentUser(HttpSession session) {
		    return (UsersEntity) session.getAttribute("user");
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
