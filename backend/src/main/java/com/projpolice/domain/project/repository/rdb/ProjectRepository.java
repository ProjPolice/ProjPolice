package com.projpolice.domain.project.repository.rdb;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.project.domain.rdb.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByUserId(long userId, Pageable pageable);

    @Query("""
        select count(p.id)>0 
        from Project p 
        where p.id = :projectId 
        and p.user.id = :userId 
        and p.deleted = false
        """)
    boolean checkOwnership(@Param("projectId") long projectId, @Param("userId") long userId);

}
