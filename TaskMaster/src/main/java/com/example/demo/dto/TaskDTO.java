package com.example.demo.dto;

import java.util.Date;

public class TaskDTO {
    private Long taskId;
    private String title;
    private String icerik;
    private String taskVerenKullaniciUsername;
    private Date createDate;

    // Getter ve Setter metodları
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public String getTaskVerenKullaniciUsername() {
        return taskVerenKullaniciUsername;
    }

    public void setTaskVerenKullaniciUsername(String taskVerenKullaniciUsername) {
        this.taskVerenKullaniciUsername = taskVerenKullaniciUsername;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
