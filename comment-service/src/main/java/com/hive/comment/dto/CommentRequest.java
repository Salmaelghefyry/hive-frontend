package com.hive.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentRequest {
    
    @NotBlank(message = "Comment content is required")
    @Size(max = 1000)
    private String content;
    
    private Long taskId;
    private Long projectId;
    
    // Constructors
    public CommentRequest() {}
    
    public CommentRequest(String content, Long taskId, Long projectId) {
        this.content = content;
        this.taskId = taskId;
        this.projectId = projectId;
    }
    
    // Getters and Setters
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
}
