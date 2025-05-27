package com.hive.task.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Task title is required")
    @Size(max = 200)
    @Column(nullable = false)
    private String title;
    
    @Size(max = 1000)
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.TODO;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority = TaskPriority.MEDIUM;
    
    @NotNull(message = "Assignee ID is required")
    @Column(name = "assignee_id", nullable = false)
    private Long assigneeId;
    
    @NotNull(message = "Project ID is required")
    @Column(name = "project_id", nullable = false)
    private Long projectId;
    
    @Column(name = "created_by", nullable = false)
    private Long createdBy;
    
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public Task() {}
    
    public Task(String title, String description, Long assigneeId, Long projectId, Long createdBy) {
        this.title = title;
        this.description = description;
        this.assigneeId = assigneeId;
        this.projectId = projectId;
        this.createdBy = createdBy;
        this.status = TaskStatus.TODO;
        this.priority = TaskPriority.MEDIUM;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
    
    public TaskPriority getPriority() { return priority; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }
    
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
    
    public enum TaskStatus {
        TODO, IN_PROGRESS, IN_REVIEW, COMPLETED
    }
    
    public enum TaskPriority {
        LOW, MEDIUM, HIGH
    }
}
