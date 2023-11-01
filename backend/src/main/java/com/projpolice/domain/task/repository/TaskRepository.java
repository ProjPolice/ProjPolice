package com.projpolice.domain.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.task.domain.Task;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("""
        UPDATE Task t
        SET t.deleted = true 
        WHERE t.epic.id = :epicId
        """)
    int deleteAllByEpicId(@Param("epicId") Long epicId);
}
