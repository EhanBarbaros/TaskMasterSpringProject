package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    @Column(nullable = false)
    private Long taskAlanKullaniciId;

    @Column(nullable = false)
    private Long taskVerenKullaniciId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String icerik;

    @Column(nullable = false)
    private boolean tamamlandi = false;

    @Column(nullable = false)
    private Date createDate = new Date();

    // Getter ve Setter metodları
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskAlanKullaniciId() {
        return taskAlanKullaniciId;
    }

    public void setTaskAlanKullaniciId(Long taskAlanKullaniciId) {
        this.taskAlanKullaniciId = taskAlanKullaniciId;
    }

    public Long getTaskVerenKullaniciId() {
        return taskVerenKullaniciId;
    }

    public void setTaskVerenKullaniciId(Long taskVerenKullaniciId) {
        this.taskVerenKullaniciId = taskVerenKullaniciId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public boolean isTamamlandi() {
        return tamamlandi;
    }

    public void setTamamlandi(boolean tamamlandi) {
        this.tamamlandi = tamamlandi;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
