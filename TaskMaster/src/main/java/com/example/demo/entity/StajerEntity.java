package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Stajerler")
public class StajerEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stajerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kullaniciId", referencedColumnName = "KullaniciId")
    private UsersEntity kullanici;
    
	public StajerEntity() {
	}

	public StajerEntity(UsersEntity kullanici) {
        this.kullanici = kullanici;
        this.kullanici.setRutbesi("Stajer");
    }

	public StajerEntity(String name, String surname, String username, String password) {
        this.kullanici = new UsersEntity();
        this.kullanici.setName(name);
        this.kullanici.setSurname(surname);
        this.kullanici.setUsername(username);
        this.kullanici.setPassword(password);
        this.kullanici.setRutbesi("Stajer");
        this.kullanici.setTakimId();
    }

	public long getId() {
		return stajerId;
	}

	public void setId(long id) {
		this.stajerId = id;
	}
	
	public UsersEntity getKullanici() {
	    return kullanici;
	}

	public void setKullanici(UsersEntity kullanici) {
	    this.kullanici = kullanici;
	}
	
}
