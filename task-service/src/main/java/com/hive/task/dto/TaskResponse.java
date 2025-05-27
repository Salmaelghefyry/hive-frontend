package com.hive.task.dto;

import com.hive.task.entity.Task;
import java.time.LocalDateTime;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private Task.TaskStatus status;
    private Task.TaskPriority priority;
    private Long assigneeId;
    private Long projectId;
    private Long createdBy;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public TaskResponse() {}
    
    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.assigneeId = task.getAssigneeId();
        this.projectId = task.getProjectId();
        this.createdBy = task.getCreatedBy();
        this.dueDate = task.getDueDate();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Task.TaskStatus getStatus() { return status; }
    public void setStatus(Task.TaskStatus status) { this.status = status; }
    
    public Task.TaskPriority getPriority() { return priority; }
    public void setPriority(Task.TaskPriority priority) { this.priority = priority; }
    
    public Long getAssigneeId() { return assigneeId; }
    public void setAssigneeId(Long assigneeId) { this.assigneeId = assigneeId; }
    
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    
    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }
    
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
