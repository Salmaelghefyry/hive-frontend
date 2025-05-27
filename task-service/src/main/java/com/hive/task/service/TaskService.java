package com.hive.task.service;

import com.hive.task.dto.TaskRequest;
import com.hive.task.dto.TaskResponse;
import com.hive.task.entity.Task;
import com.hive.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    public TaskResponse createTask(TaskRequest request, Long createdBy) {
        Task task = new Task(
            request.getTitle(),
            request.getDescription(),
            request.getAssigneeId(),
            request.getProjectId(),
            createdBy
        );
        
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        
        task = taskRepository.save(task);
        return new TaskResponse(task);
    }
    
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }
    
    public List<TaskResponse> getTasksByAssignee(Long assigneeId) {
        return taskRepository.findByAssigneeId(assigneeId).stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }
    
    public List<TaskResponse> getTasksByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId).stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }
    
    public List<TaskResponse> getTasksByCreator(Long createdBy) {
        return taskRepository.findByCreatedBy(createdBy).stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }
    
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return new TaskResponse(task);
    }
    
    public TaskResponse updateTask(Long id, TaskRequest request, Long userId) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        // Check if user can update this task (creator or assignee)
        if (!task.getCreatedBy().equals(userId) && !task.getAssigneeId().equals(userId)) {
            throw new RuntimeException("You don't have permission to update this task");
        }
        
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        
        // Only creator can change assignee
        if (task.getCreatedBy().equals(userId)) {
            task.setAssigneeId(request.getAssigneeId());
        }
        
        task = taskRepository.save(task);
        return new TaskResponse(task);
    }
    
    public TaskResponse updateTaskStatus(Long id, Task.TaskStatus status, Long userId) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        // Check if user can update status (assignee or creator)
        if (!task.getAssigneeId().equals(userId) && !task.getCreatedBy().equals(userId)) {
            throw new RuntimeException("You don't have permission to update this task status");
        }
        
        task.setStatus(status);
        task = taskRepository.save(task);
        return new TaskResponse(task);
    }
    
    public void deleteTask(Long id, Long userId) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        // Only creator can delete task
        if (!task.getCreatedBy().equals(userId)) {
            throw new RuntimeException("Only task creator can delete the task");
        }
        
        taskRepository.delete(task);
    }
    
    public List<TaskResponse> searchTasks(String keyword) {
        return taskRepository.findByTitleOrDescriptionContainingIgnoreCase(keyword).stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }
    
    public List<TaskResponse> getTasksByStatus(Task.TaskStatus status) {
        return taskRepository.findByStatus(status).stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }
    
    public List<TaskResponse> getTasksByPriority(Task.TaskPriority priority) {
        return taskRepository.findByPriority(priority).stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }
    
    public Double getProjectProgress(Long projectId) {
        Long totalTasks = taskRepository.countTotalTasksByProject(projectId);
        if (totalTasks == 0) {
            return 0.0;
        }
        
        Long completedTasks = taskRepository.countCompletedTasksByProject(projectId);
        return (completedTasks.doubleValue() / totalTasks.doubleValue()) * 100;
    }
}
