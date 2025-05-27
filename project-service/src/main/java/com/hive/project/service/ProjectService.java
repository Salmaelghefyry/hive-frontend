package com.hive.project.service;

import com.hive.project.dto.ProjectRequest;
import com.hive.project.dto.ProjectResponse;
import com.hive.project.entity.Project;
import com.hive.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    public ProjectResponse createProject(ProjectRequest request, Long leaderId) {
        Project project = new Project(request.getName(), request.getDescription(), leaderId);
        if (request.getTags() != null) {
            project.setTags(request.getTags());
        }
        
        project = projectRepository.save(project);
        return new ProjectResponse(project);
    }
    
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(ProjectResponse::new)
                .collect(Collectors.toList());
    }
    
    public List<ProjectResponse> getProjectsByLeader(Long leaderId) {
        return projectRepository.findByLeaderId(leaderId).stream()
                .map(ProjectResponse::new)
                .collect(Collectors.toList());
    }
    
    public List<ProjectResponse> getProjectsByUserInvolvement(Long userId) {
        return projectRepository.findProjectsByUserInvolvement(userId, userId).stream()
                .map(ProjectResponse::new)
                .collect(Collectors.toList());
    }
    
    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return new ProjectResponse(project);
    }
    
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        
        // Check if user is the project leader
        if (!project.getLeaderId().equals(userId)) {
            throw new RuntimeException("Only project leader can update the project");
        }
        
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        if (request.getTags() != null) {
            project.setTags(request.getTags());
        }
        
        project = projectRepository.save(project);
        return new ProjectResponse(project);
    }
    
    public ProjectResponse updateProjectStatus(Long id, Project.ProjectStatus status, Long userId) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        
        // Check if user is the project leader
        if (!project.getLeaderId().equals(userId)) {
            throw new RuntimeException("Only project leader can update project status");
        }
        
        project.setStatus(status);
        project = projectRepository.save(project);
        return new ProjectResponse(project);
    }
    
    public ProjectResponse updateProjectProgress(Long id, Integer progress) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        
        project.setProgress(Math.max(0, Math.min(100, progress))); // Ensure progress is between 0-100
        project = projectRepository.save(project);
        return new ProjectResponse(project);
    }
    
    public void deleteProject(Long id, Long userId) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        
        // Check if user is the project leader
        if (!project.getLeaderId().equals(userId)) {
            throw new RuntimeException("Only project leader can delete the project");
        }
        
        projectRepository.delete(project);
    }
    
    public List<ProjectResponse> searchProjects(String keyword) {
        return projectRepository.findByNameOrDescriptionContainingIgnoreCase(keyword).stream()
                .map(ProjectResponse::new)
                .collect(Collectors.toList());
    }
}
