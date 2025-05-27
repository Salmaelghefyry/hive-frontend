package com.hive.comment.repository;

import com.hive.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTaskId(Long taskId);
    List<Comment> findByProjectId(Long projectId);
    List<Comment> findByAuthorId(Long authorId);
    
    @Query("SELECT c FROM Comment c WHERE c.taskId = :taskId ORDER BY c.createdAt ASC")
    List<Comment> findByTaskIdOrderByCreatedAt(@Param("taskId") Long taskId);
    
    @Query("SELECT c FROM Comment c WHERE c.projectId = :projectId ORDER BY c.createdAt DESC")
    List<Comment> findByProjectIdOrderByCreatedAtDesc(@Param("projectId") Long projectId);
    
    @Query("SELECT c FROM Comment c WHERE c.authorId = :authorId ORDER BY c.createdAt DESC")
    List<Comment> findByAuthorIdOrderByCreatedAtDesc(@Param("authorId") Long authorId);
    
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.taskId = :taskId")
    Long countByTaskId(@Param("taskId") Long taskId);
    
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.projectId = :projectId")
    Long countByProjectId(@Param("projectId") Long projectId);
}
