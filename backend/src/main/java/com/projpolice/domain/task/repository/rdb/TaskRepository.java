package com.projpolice.domain.task.repository.rdb;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.task.domain.rdb.Task;
import com.projpolice.domain.task.dto.ProjectDetailProjection;
import com.projpolice.domain.task.dto.ProjectIdEpicIdProjectionData;
import com.projpolice.domain.task.dto.TaskNameProjectNameOwnerNameProjectionData;
import com.projpolice.domain.task.dto.UserTaskProjectionData;
import com.projpolice.domain.user.dto.UserEmailFcmTokenProjection;

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

    @Modifying(clearAutomatically = true)
    @Query("""
        update Task task
        set task.deleted = true
        where task.deleted = false
         and task.epic.id in 
            (
                select epic.id
                from Epic epic
                left join Project project on epic.project.id = project.id
                where project.id = :projectId and project.deleted = false
            )
        """)
    int deleteAllByProjectId(@Param("projectId") long projectId);

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
        left join UserProject userProject on epic.project.id = userProject.project.id
        where task.deleted = false
        and task.id = :taskId
        and userProject.user.id = :userId
        """)
    boolean checkMembership(@Param("taskId") long taskId, @Param("userId") long userId);

    @Query("""
        select epic.project.id
        from Task task
        left join Epic epic on task.epic.id = epic.id
        where task.deleted = false and task.id = :taskId
        """)
    Optional<Long> findProjectIdById(@Param("taskId") long taskId);

    @Query("""
        select epic.project.id as projectId, epic.id as epicId
        from Task task
        left join Epic epic on task.epic.id = epic.id
        where task.deleted = false and task.id = :taskId
        """)
    Optional<ProjectIdEpicIdProjectionData> findProjectIdEpicIdById(@Param("taskId") long taskId);

    @Query("""
        select 
            task.id as taskId,
            task.name as taskName,
            task.startDate as startDate,
            task.endDate as endDate,
            epic.id as epicId,
            epic.name as epicName,
            project.id as projectId,
            project.name as projectName,
            file.id as fileId,
            file.name as fileName,
            coalesce(task.status, 'TODO') as taskStatus,
            userProject.id as userId
        from Task task
        left join Epic epic on task.epic.id = epic.id
        left join Project project on epic.project.id = project.id
        left join UserProject userProject on project.id = userProject.project.id
        left outer join File file on task.id = file.task.id
        where task.deleted = false and (file.deleted = false or file is null)
         and userProject.user.id = :userId
         and 
          (
             (task.startDate is null and task.endDate is null)
            or
             (task.startDate between :startDate and :endDate)
            or
             (task.endDate between :startDate and :endDate)
            or
             (epic.startDate between :startDate and :endDate)
            or
             (epic.endDate between :startDate and :endDate)
          )
        """)
    List<UserTaskProjectionData> findTasksByUserId(@Param("userId") long userId,
        @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("""
        select task.name as taskName, project.name as projectName, project.user.name as projectOwnerName
        from Task task
        left join Epic epic on task.epic.id = epic.id
        left join Project project on epic.project.id = project.id
        where task.deleted = false and task.id = :taskId
        """)
    Optional<TaskNameProjectNameOwnerNameProjectionData> findNameProjectNameProjectionById(
        @Param("taskId") long taskId);

    @Query("""
        select 
         userProject.user.email as email,
         userProject.user.fcmToken as fcmToken
        from Task task
        left join Epic epic on task.epic.id = epic.id
        left join UserProject userProject on epic.project.id = userProject.project.id
        where task.deleted = false and task.id = :taskId and userProject.user.id != :userId
        """)
    List<UserEmailFcmTokenProjection> findOtherUsersInTasksById(@Param("taskId") long taskId,
        @Param("userId") long userId);

    @Query("""
        select
         task.id as taskId,
         task.name as taskName,
         task.startDate as startDate,
         task.endDate as endDate,
         coalesce(task.status, 'TODO') as taskStatus,
         task.user.id as userId,
         task.user.name as userName,
         task.user.image as userImage,
         epic.id as epicId,
         epic.name as epicName,
         file.id as fileId,
         file.name as fileName,
         file.comment as fileComment,
         file.version as fileVersion,
         file.extension as fileExtension,
         file.createdAt as fileCreatedAt
        from Task task
        left join Epic epic on task.epic.id = epic.id
        left join Project project on project.id = epic.project.id
        left outer join File file on file.task.id = task.id
        where task.deleted = false and project.id = :projectId
        """)
    List<ProjectDetailProjection> findProjectTaskDetailsByProjectId(@Param("projectId") long projectId);

    @Query("""
        select
         task.id as taskId,
         task.name as taskName,
         task.startDate as startDate,
         task.endDate as endDate,
         coalesce(task.status, 'TODO') as taskStatus,
         task.user.id as userId,
         task.user.name as userName,
         task.user.image as userImage,
         file.id as fileId,
         file.name as fileName,
         file.comment as fileComment,
         file.version as fileVersion,
         file.extension as fileExtension,
         file.createdAt as fileCreatedAt,
         epic.id as epicId,
         epic.name as epicName
        from Task task
        left join Epic epic on task.epic.id = epic.id
        left outer join File file on file.task.id = task.id
        where task.deleted = false and epic.id = :epicId
        """)
    List<ProjectDetailProjection> findEpicTaskDetailsByEpicId(@Param("epicId") long epicId);
}
