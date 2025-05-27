package com.hive.project.dto;

import com.hive.project.entity.Project;
import java.time.LocalDateTime;
import java.util.List;

public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private Project.ProjectStatus status;
    private Long leaderId;
    private Integer memberCount;
    private Integer progress;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public ProjectResponse() {}
    
    public ProjectResponse(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.status = project.getStatus();
        this.leaderId = project.getLeaderId();
        this.memberCount = project.getMemberCount();
        this.progress = project.getProgress();
        this.tags = project.getTags();
        this.createdAt = project.getCreatedAt();
        this.updatedAt = project.getUpdatedAt();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Project.ProjectStatus getStatus() { return status; }
    public void setStatus(Project.ProjectStatus status) { this.status = status; }
    
    public Long getLeaderId() { return leaderId; }
    public void setLeaderId(Long leaderId) { this.leaderId = leaderId; }
    
    public Integer getMemberCount() { return memberCount; }
    public void setMemberCount(Integer memberCount) { this.memberCount = memberCount; }
    
    public Integer getProgress() { return progress; }
    public void setProgress(Integer progress) { this.progress = progress; }
    
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
