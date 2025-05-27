package com.hive.task.dto;

import com.hive.task.entity.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class TaskRequest {
    
    @NotBlank(message = "Task title is required")
    @Size(max = 200)
    private String title;
    
    @Size(max = 1000)
    private String description;
    
    private Task.TaskPriority priority = Task.TaskPriority.MEDIUM;
    
    @NotNull(message = "Assignee ID is required")
    private Long assigneeId;
    
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    private LocalDateTime dueDate;
    
    // Constructors
    public TaskRequest() {}
    
    public TaskRequest(String title, String description, Task.TaskPriority priority, 
                      Long assigneeId, Long projectId, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.assigneeId = assigneeId;
        this.projectId = projectId;
        this.dueDate = dueDate;
    }
    
    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Task.TaskPriority getPriority() { return priority; }
    public void setPriority(Task.TaskPriority priority) { this.priority = priority; }
    
    public Long getAssigneeId() { return assigneeId; }
    public void setAssigneeId(Long assigneeId) { this.assigneeId = assigneeId; }
    
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
}
