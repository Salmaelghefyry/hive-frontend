package com.hive.comment.service;

import com.hive.comment.dto.CommentRequest;
import com.hive.comment.dto.CommentResponse;
import com.hive.comment.entity.Comment;
import com.hive.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
    
    public CommentResponse createComment(CommentRequest request, Long authorId) {
        Comment comment = new Comment(
            request.getContent(),
            authorId,
            request.getTaskId(),
            request.getProjectId()
        );
        
        comment = commentRepository.save(comment);
        return new CommentResponse(comment);
    }
    
    public List<CommentResponse> getAllComments() {
        return commentRepository.findAll().stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }
    
    public List<CommentResponse> getCommentsByTask(Long taskId) {
        return commentRepository.findByTaskIdOrderByCreatedAt(taskId).stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }
    
    public List<CommentResponse> getCommentsByProject(Long projectId) {
        return commentRepository.findByProjectIdOrderByCreatedAtDesc(projectId).stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }
    
    public List<CommentResponse> getCommentsByAuthor(Long authorId) {
        return commentRepository.findByAuthorIdOrderByCreatedAtDesc(authorId).stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }
    
    public CommentResponse getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return new CommentResponse(comment);
    }
    
    public CommentResponse updateComment(Long id, CommentRequest request, Long userId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        
        // Check if user is the author of the comment
        if (!comment.getAuthorId().equals(userId)) {
            throw new RuntimeException("You can only update your own comments");
        }
        
        comment.setContent(request.getContent());
        comment = commentRepository.save(comment);
        return new CommentResponse(comment);
    }
    
    public void deleteComment(Long id, Long userId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        
        // Check if user is the author of the comment
        if (!comment.getAuthorId().equals(userId)) {
            throw new RuntimeException("You can only delete your own comments");
        }
        
        commentRepository.delete(comment);
    }
    
    public Long getTaskCommentCount(Long taskId) {
        return commentRepository.countByTaskId(taskId);
    }
    
    public Long getProjectCommentCount(Long projectId) {
        return commentRepository.countByProjectId(projectId);
    }
}
