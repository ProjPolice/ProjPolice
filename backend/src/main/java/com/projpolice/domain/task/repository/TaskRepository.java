package com.projpolice.domain.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.task.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
