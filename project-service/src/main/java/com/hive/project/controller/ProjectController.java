package com.hive.project.controller;

import com.hive.project.dto.ProjectRequest;
import com.hive.project.dto.ProjectResponse;
import com.hive.project.entity.Project;
import com.hive.project.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/project")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        List<ProjectResponse> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/my")
    public ResponseEntity<List<ProjectResponse>> getMyProjects(Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        List<ProjectResponse> projects = projectService.getProjectsByUserInvolvement(userId);
        return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/leader")
    @PreAuthorize("hasRole('PROJECT_LEADER') or hasRole('PROJECT_ADMIN')")
    public ResponseEntity<List<ProjectResponse>> getProjectsByLeader(Authentication authentication) {
        Long leaderId = getUserIdFromAuthentication(authentication);
        List<ProjectResponse> projects = projectService.getProjectsByLeader(leaderId);
        return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        ProjectResponse project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('PROJECT_LEADER') or hasRole('PROJECT_ADMIN')")
    public ResponseEntity<ProjectResponse> createProject(
            @Valid @RequestBody ProjectRequest request,
            Authentication authentication) {
        Long leaderId = getUserIdFromAuthentication(authentication);
        ProjectResponse project = projectService.createProject(request, leaderId);
        return ResponseEntity.ok(project);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROJECT_LEADER') or hasRole('PROJECT_ADMIN')")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest request,
            Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        ProjectResponse project = projectService.updateProject(id, request, userId);
        return ResponseEntity.ok(project);
    }
    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('PROJECT_LEADER') or hasRole('PROJECT_ADMIN')")
    public ResponseEntity<ProjectResponse> updateProjectStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request,
            Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        Project.ProjectStatus status = Project.ProjectStatus.valueOf(request.get("status"));
        ProjectResponse project = projectService.updateProjectStatus(id, status, userId);
        return ResponseEntity.ok(project);
    }
    
    @PutMapping("/{id}/progress")
    public ResponseEntity<ProjectResponse> updateProjectProgress(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> request) {
        Integer progress = request.get("progress");
        ProjectResponse project = projectService.updateProjectProgress(id, progress);
        return ResponseEntity.ok(project);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROJECT_LEADER') or hasRole('PROJECT_ADMIN')")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long id,
            Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        projectService.deleteProject(id, userId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ProjectResponse>> searchProjects(@RequestParam String keyword) {
        List<ProjectResponse> projects = projectService.searchProjects(keyword);
        return ResponseEntity.ok(projects);
    }
    
    private Long getUserIdFromAuthentication(Authentication authentication) {
        // This would typically extract user ID from JWT token
        // For now, returning a mock ID - implement based on your JWT structure
        return 1L;
    }
}
