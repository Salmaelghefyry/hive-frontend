package com.hive.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@EntityListeners(AuditingEntityListener.class)
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Project name is required")
    @Size(max = 100)
    @Column(nullable = false)
    private String name;
    
    @Size(max = 500)
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status = ProjectStatus.PLANNING;
    
    @Column(name = "leader_id", nullable = false)
    private Long leaderId;
    
    @Column(name = "member_count")
    private Integer memberCount = 0;
    
    @Column(name = "progress")
    private Integer progress = 0;
    
    @ElementCollection
    @CollectionTable(name = "project_tags", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public Project() {}
    
    public Project(String name, String description, Long leaderId) {
        this.name = name;
        this.description = description;
        this.leaderId = leaderId;
        this.status = ProjectStatus.PLANNING;
        this.memberCount = 1; // Leader is automatically a member
        this.progress = 0;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public ProjectStatus getStatus() { return status; }
    public void setStatus(ProjectStatus status) { this.status = status; }
    
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
    
    public enum ProjectStatus {
        PLANNING, ACTIVE, ON_HOLD, COMPLETED
    }
}
