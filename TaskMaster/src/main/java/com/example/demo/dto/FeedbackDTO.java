package com.example.demo.dto;

public class FeedbackDTO {
    private Long taskId;
    private String feedbackContent;

    // Getter ve Setter metodları
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }
}
