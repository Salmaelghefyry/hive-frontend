package com.hive.comment.dto;

import com.hive.comment.entity.Comment;
import java.time.LocalDateTime;

public class CommentResponse {
    private Long id;
    private String content;
    private Long authorId;
    private Long taskId;
    private Long projectId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public CommentResponse() {}
    
    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.authorId = comment.getAuthorId();
        this.taskId = comment.getTaskId();
        this.projectId = comment.getProjectId();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
    
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
