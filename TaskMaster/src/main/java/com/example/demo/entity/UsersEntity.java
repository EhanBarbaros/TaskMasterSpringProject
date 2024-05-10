package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Kullanicilar")
public class UsersEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long KullaniciId;
	
	@Column(name="takimId")
	private Long TakimId =(long) 0;
	
	@Column
	private String rutbesi;

	@Column
	private String name;

	@Column 
	private String surname;

	@Column(nullable = false, unique = true) 
	private String username;
	
	@Column 
	private String password;
	
	public UsersEntity() {
	}

	public UsersEntity(long id , String rutbesi,String name, String surname, String username,String password) {  
		this.KullaniciId = id;
		this.rutbesi = rutbesi;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return KullaniciId;
	}

	public void setId(long id) {
		this.KullaniciId = id;
	}

	public Long getTakimId() {
		return TakimId;
	}

	public void setTakimId( ) {
		this.TakimId = (long) 0;
	}
	
	public void setTakimId(long takimId ) {
		this.TakimId = takimId;
	}

	public String getRutbesi() {
		return rutbesi;
	}

	 public void setRutbesi(String rutbesi) {
	        this.rutbesi = rutbesi;
	    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
