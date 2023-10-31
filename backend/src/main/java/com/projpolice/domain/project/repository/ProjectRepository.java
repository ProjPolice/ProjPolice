package com.projpolice.domain.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.project.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
