package com.projpolice.domain.epic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.epic.domain.Epic;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Long> {
}
