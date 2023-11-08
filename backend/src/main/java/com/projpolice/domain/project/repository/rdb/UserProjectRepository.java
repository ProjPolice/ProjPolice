package com.projpolice.domain.project.repository.rdb;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.project.domain.rdb.UserProject;
import com.projpolice.domain.user.domain.User;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Long> {

    @Query("""
            select user
            from UserProject
            where project.id = :project_id
        """)
    List<User> findUserByProjectId(@Param("project_id") Long projectId);

    Optional<UserProject> findByProjectIdAndUserId(long projectId, long userId);

    @Query("""
            select count(userProject.id)>0
            from UserProject userProject
            where userProject.project.id = :project_id
            and userProject.user.id = :user_id
            and userProject.deleted = false and userProject.project.deleted = false
        """)
    boolean checkMembership(@Param("project_id") long projectId, @Param("user_id") long userId);
}
