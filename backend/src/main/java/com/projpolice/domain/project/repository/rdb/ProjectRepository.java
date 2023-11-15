package com.projpolice.domain.project.repository.rdb;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.project.domain.rdb.Project;
import com.projpolice.domain.project.dto.ProjectNameUserNameProjection;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByUserId(long userId, Pageable pageable);

    @EntityGraph
    Optional<Project> findFetchById(long id);

    @Query("""
        select project.name as projectName, project.user.name as userName
        from Project project
        where project.id = :projectId and project.deleted = false
        """)
    Optional<ProjectNameUserNameProjection> findNameUserNameProjectionById(@Param("projectId") long projectId);

    @Query("""
        select count(p.id)>0 
        from Project p 
        where p.id = :projectId 
        and p.user.id = :userId 
        and p.deleted = false
        """)
    boolean checkOwnership(@Param("projectId") long projectId, @Param("userId") long userId);

    // 해당 유저가 속해있는 프로젝트
    @Query("""
            select userProject.project
            from UserProject userProject
            where userProject.user.id = :userId
            and userProject.deleted = false and userProject.project.deleted = false
        """)
    Page<Project> findByProjectUserId(long userId, Pageable pageable);
}
