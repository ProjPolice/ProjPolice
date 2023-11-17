package com.projpolice.domain.file.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projpolice.domain.file.domain.File;
import com.projpolice.domain.file.dto.FileWithUserIdProjectionData;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findByTaskId(Long taskId);

    @Query("""
        select user.id
        from File file
        left join Task task on file.task.id = task.id
        left join User user on task.user.id = user.id
        where file.id = :fileId and file.deleted = false
        """)
    Optional<Long> findUserIdById(Long fileId);

    @Query("""
        select file, user.id as userId
        from File file
        left join Task task on file.task.id = task.id
        left join User user on task.user.id = user.id
        where file.id = :fileId and file.deleted = false
        """)
    Optional<FileWithUserIdProjectionData> findFileUserIdProjectionById(@Param("fileId") Long fileId);

    @Query("""
        select file
        from File file
        where (file.task.id, file.createdAt) in (
            select file.task.id as id, max(file.createdAt) as createdAt
            from File file
            where file.task.epic.project.id = :projectId and file.deleted = false 
            group by file.task.id
        )
        """)
    List<File> findByProjectId(@Param("projectId") Long projectId);

    @Query("""
        select file.uuid
        from File file
        where file.task.id = :taskId and file.deleted = false
        """)
    List<String> listUuidByTaskId(@Param("taskId") Long taskId);

    @Modifying(clearAutomatically = true)
    @Query("""
        update File file
        set file.deleted = true
        where file.task.id = :taskId
        """)
    int deleteAllByTaskId(@Param("taskId") Long taskId);

    @Query("""
        select file.uuid
        from File file
        left join Task task on file.task.id = task.id
        where task.epic.id = :epicId and file.deleted = false
        """)
    List<String> listUuidByEpicId(@Param("epicId") Long epicId);

    @Modifying(clearAutomatically = true)
    @Query("""
        update File file
        set file.deleted = true
        where file.task.id in (
            select task.id 
            from Task task
            where task.epic.id = :epicId
            )
        """)
    int deleteAllByEpicId(@Param("epicId") Long epicId);

    @Query("""
        select file.uuid
        from File file
        left join Task task on file.task.id = task.id
        left join Epic epic on task.epic.id = epic.id
        where epic.project.id = :projectId and file.deleted = false
        """)
    List<String> listUuidByProjectId(@Param("projectId") Long projectId);

    @Modifying(clearAutomatically = true)
    @Query("""
        update File file
        set file.deleted = true
        where file.deleted = false
         and file.task.id in (
            select task.id 
            from Task task
            left join Epic epic on task.epic.id = epic.id
            where epic.project.id = :projectId
            )
        """)
    int deleteAllByProjectId(@Param("projectId") Long projectId);

    @Modifying(clearAutomatically = true)
    @Query("""
        update File file
        set file.deleted = true
        where file.uuid in (:uuid) and file.deleted = false
        """)
    void deleteAllByUuid(@Param("uuid") List<String> uuid);
}
