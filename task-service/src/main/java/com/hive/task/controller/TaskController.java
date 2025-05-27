package com.hive.task.controller;

import com.hive.task.dto.TaskRequest;
import com.hive.task.dto.TaskResponse;
import com.hive.task.entity.Task;
import com.hive.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/task")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/my")
    public ResponseEntity<List<TaskResponse>> getMyTasks(Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        List<TaskResponse> tasks = taskService.getTasksByAssignee(userId);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/created")
    @PreAuthorize("hasRole('PROJECT_LEADER') or hasRole('PROJECT_ADMIN')")
    public ResponseEntity<List<TaskResponse>> getCreatedTasks(Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        List<TaskResponse> tasks = taskService.getTasksByCreator(userId);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponse>> getTasksByProject(@PathVariable Long projectId) {
        List<TaskResponse> tasks = taskService.getTasksByProject(projectId);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        TaskResponse task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('PROJECT_LEADER') or hasRole('PROJECT_ADMIN')")
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest request,
            Authentication authentication) {
        Long createdBy = getUserIdFromAuthentication(authentication);
        TaskResponse task = taskService.createTask(request, createdBy);
        return ResponseEntity.ok(task);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request,
            Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        TaskResponse task = taskService.updateTask(id, request, userId);
        return ResponseEntity.ok(task);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request,
            Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        Task.TaskStatus status = Task.TaskStatus.valueOf(request.get("status"));
        TaskResponse task = taskService.updateTaskStatus(id, status, userId);
        return ResponseEntity.ok(task);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROJECT_LEADER') or hasRole('PROJECT_ADMIN')")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id,
            Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        taskService.deleteTask(id, userId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> searchTasks(@RequestParam String keyword) {
        List<TaskResponse> tasks = taskService.searchTasks(keyword);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponse>> getTasksByStatus(@PathVariable String status) {
        Task.TaskStatus taskStatus = Task.TaskStatus.valueOf(status.toUpperCase());
        List<TaskResponse> tasks = taskService.getTasksByStatus(taskStatus);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponse>> getTasksByPriority(@PathVariable String priority) {
        Task.TaskPriority taskPriority = Task.TaskPriority.valueOf(priority.toUpperCase());
        List<TaskResponse> tasks = taskService.getTasksByPriority(taskPriority);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/project/{projectId}/progress")
    public ResponseEntity<Map<String, Double>> getProjectProgress(@PathVariable Long projectId) {
        Double progress = taskService.getProjectProgress(projectId);
        return ResponseEntity.ok(Map.of("progress", progress));
    }
    
    private Long getUserIdFromAuthentication(Authentication authentication) {
        // This would typically extract user ID from JWT token
        // For now, returning a mock ID - implement based on your JWT structure
        return 1L;
    }
}
