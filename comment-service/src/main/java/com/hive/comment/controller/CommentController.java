package com.hive.comment.controller;

import com.hive.comment.dto.CommentRequest;
import com.hive.comment.dto.CommentResponse;
import com.hive.comment.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/comment")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAllComments() {
        List<CommentResponse> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }
    
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByTask(@PathVariable Long taskId) {
        List<CommentResponse> comments = commentService.getCommentsByTask(taskId);
        return ResponseEntity.ok(comments);
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByProject(@PathVariable Long projectId) {
        List<CommentResponse> comments = commentService.getCommentsByProject(projectId);
        return ResponseEntity.ok(comments);
    }
    
    @GetMapping("/my")
    public ResponseEntity<List<CommentResponse>> getMyComments(Authentication authentication) {
        Long authorId = getUserIdFromAuthentication(authentication);
        List<CommentResponse> comments = commentService.getCommentsByAuthor(authorId);
        return ResponseEntity.ok(comments);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable Long id) {
        CommentResponse comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }
    
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @Valid @RequestBody CommentRequest request,
            Authentication authentication) {
        Long authorId = getUserIdFromAuthentication(authentication);
        CommentResponse comment = commentService.createComment(request, authorId);
        return ResponseEntity.ok(comment);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long id,
            @Valid @RequestBody CommentRequest request,
            Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        CommentResponse comment = commentService.updateComment(id, request, userId);
        return ResponseEntity.ok(comment);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long id,
            Authentication authentication) {
        Long userId = getUserIdFromAuthentication(authentication);
        commentService.deleteComment(id, userId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/task/{taskId}/count")
    public ResponseEntity<Map<String, Long>> getTaskCommentCount(@PathVariable Long taskId) {
        Long count = commentService.getTaskCommentCount(taskId);
        return ResponseEntity.ok(Map.of("count", count));
    }
    
    @GetMapping("/project/{projectId}/count")
    public ResponseEntity<Map<String, Long>> getProjectCommentCount(@PathVariable Long projectId) {
        Long count = commentService.getProjectCommentCount(projectId);
        return ResponseEntity.ok(Map.of("count", count));
    }
    
    private Long getUserIdFromAuthentication(Authentication authentication) {
        // This would typically extract user ID from JWT token
        // For now, returning a mock ID - implement based on your JWT structure
        return 1L;
    }
}
