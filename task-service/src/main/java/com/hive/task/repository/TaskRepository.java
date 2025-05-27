package com.hive.task.repository;

import com.hive.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssigneeId(Long assigneeId);
    List<Task> findByProjectId(Long projectId);
    List<Task> findByCreatedBy(Long createdBy);
    List<Task> findByStatus(Task.TaskStatus status);
    List<Task> findByPriority(Task.TaskPriority priority);
    
    @Query("SELECT t FROM Task t WHERE t.assigneeId = :assigneeId AND t.status = :status")
    List<Task> findByAssigneeIdAndStatus(@Param("assigneeId") Long assigneeId, 
                                        @Param("status") Task.TaskStatus status);
    
    @Query("SELECT t FROM Task t WHERE t.projectId = :projectId AND t.status = :status")
    List<Task> findByProjectIdAndStatus(@Param("projectId") Long projectId, 
                                       @Param("status") Task.TaskStatus status);
    
    @Query("SELECT t FROM Task t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Task> findByTitleOrDescriptionContainingIgnoreCase(@Param("keyword") String keyword);
    
    @Query("SELECT COUNT(t) FROM Task t WHERE t.projectId = :projectId AND t.status = 'COMPLETED'")
    Long countCompletedTasksByProject(@Param("projectId") Long projectId);
    
    @Query("SELECT COUNT(t) FROM Task t WHERE t.projectId = :projectId")
    Long countTotalTasksByProject(@Param("projectId") Long projectId);
}
