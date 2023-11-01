package com.projpolice.domain.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.project.domain.UserProject;
import com.projpolice.domain.user.domain.User;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Long> {

    @Query("""
            select user
            from UserProject
            where project.id =: projectId
        """)
    List<User> findByProjectId(@Param("projectId") Long projectId);
}
