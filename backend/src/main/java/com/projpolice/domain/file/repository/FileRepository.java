package com.projpolice.domain.file.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projpolice.domain.file.domain.File;
import com.projpolice.domain.file.dto.FileDetailItem;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findByTaskId(Long taskId);
}
