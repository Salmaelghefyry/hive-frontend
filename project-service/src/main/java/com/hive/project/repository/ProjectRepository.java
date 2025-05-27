package com.hive.project.repository;

import com.hive.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByLeaderId(Long leaderId);
    
    @Query("SELECT p FROM Project p WHERE p.leaderId = :leaderId OR p.id IN " +
           "(SELECT DISTINCT t.projectId FROM Task t WHERE t.assigneeId = :userId)")
    List<Project> findProjectsByUserInvolvement(@Param("leaderId") Long leaderId, @Param("userId") Long userId);
    
    List<Project> findByStatus(Project.ProjectStatus status);
    
    @Query("SELECT p FROM Project p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Project> findByNameOrDescriptionContainingIgnoreCase(@Param("keyword") String keyword);
}
