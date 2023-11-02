package com.projpolice.domain.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.project.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByUserId(long userId, Pageable pageable);

    @Query("""
        select count(p.id) from Project p where p.id =: project_id and p.user.id =: user_id and p.deleted = false
        """)
    boolean checkOwnership(@Param("project_id") long projectId, @Param("user_id") long userId);
}
