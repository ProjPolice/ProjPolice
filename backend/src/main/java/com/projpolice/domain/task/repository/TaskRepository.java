package com.projpolice.domain.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.task.domain.Task;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Modifying(clearAutomatically = true)
    @Query("""
        UPDATE Task t
        SET t.deleted = true 
        WHERE t.epic.id = :epicId
        """)
    int deleteAllByEpicId(@Param("epicId") Long epicId);

    @Query("""
        select count(task.id)>0
        from Task task
        where task.id = :taskId 
        and task.user.id = :userId
        and task.deleted = false 
        """)
    boolean checkOwnership(@Param("taskId") long taskId, @Param("userId") long userId);

    @Query("""
        select count(task.id)>0
        from Task task
        left join Epic epic on task.epic.id = epic.id
        left join UserProject userProject on epic.project.id = userProject.id
        where task.deleted = false
        and task.id = :taskId
        and userProject.user.id = :userId
        """)
    boolean checkMembership(@Param("taskId") long taskId, @Param("userId") long userId);
}
