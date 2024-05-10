package com.example.demo.dto;

public class MeetingRequest {
    private String meetingTime;
    private boolean selectInterns;
    private boolean selectEngineers;
    private Long takimId;

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public boolean isSelectInterns() {
        return selectInterns;
    }

    public void setSelectInterns(boolean selectInterns) {
        this.selectInterns = selectInterns;
    }

    public boolean isSelectEngineers() {
        return selectEngineers;
    }

    public void setSelectEngineers(boolean selectEngineers) {
        this.selectEngineers = selectEngineers;
    }

    public Long getTakimId() {
        return takimId;
    }

    public void setTakimId(Long takimId) {
        this.takimId = takimId;
    }
}
