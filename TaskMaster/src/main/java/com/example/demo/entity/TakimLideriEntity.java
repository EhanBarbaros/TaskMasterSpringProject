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
@Table(name="TakimLiderleri")
public class TakimLideriEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long takimLideriId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kullaniciId", referencedColumnName = "KullaniciId")
    private UsersEntity kullanici;
    
	public TakimLideriEntity() {
	}

	public TakimLideriEntity(UsersEntity kullanici) {
        this.kullanici = kullanici;
        this.kullanici.setRutbesi("TakimLideri");
    }

	public TakimLideriEntity(String name, String surname, String username, String password) {
        this.kullanici = new UsersEntity();
        this.kullanici.setName(name);
        this.kullanici.setSurname(surname);
        this.kullanici.setUsername(username);
        this.kullanici.setPassword(password);
        this.kullanici.setRutbesi("TakimLideri");
        this.kullanici.setTakimId();
    }

	public long getId() {
		return takimLideriId;
	}

	public void setId(long id) {
		this.takimLideriId = id;
	}
	
	public UsersEntity getKullanici() {
	    return kullanici;
	}

	public void setKullanici(UsersEntity kullanici) {
	    this.kullanici = kullanici;
	}
	
}
