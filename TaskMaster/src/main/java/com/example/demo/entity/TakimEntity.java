package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Takimlar")
public class TakimEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long takimId;

    @Column
    private long takimLideriKullaniciId;

    @Column
    private int uyeSayisi;

    @Column(unique = true)
    private String takimAdi;

    public TakimEntity() {
    }

    public Long getTakimId() {
        return takimId;
    }

    public void setTakimId(long takimId) {
        this.takimId = takimId;
    }

    public long getTakimLideriKullaniciId() {
        return takimLideriKullaniciId;
    }

    public void setTakimLideriKullaniciId(long takimLideriKullaniciId) {
        this.takimLideriKullaniciId = takimLideriKullaniciId;
    }

    public int getUyeSayisi() {
        return uyeSayisi;
    }

    public void setUyeSayisi(int uyeSayisi) {
        this.uyeSayisi = uyeSayisi;
    }

    public String getTakimAdi() {
        return takimAdi;
    }

    public void setTakimAdi(String takimAdi) {
        this.takimAdi = takimAdi;
    }
}
