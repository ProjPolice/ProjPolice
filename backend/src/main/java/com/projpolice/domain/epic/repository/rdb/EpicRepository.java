package com.projpolice.domain.epic.repository.rdb;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.epic.domain.rdb.Epic;
import com.projpolice.domain.epic.dto.EpicProjectionDataItem;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Long> {
    @Query("""
        select count(epic.id)>0
        from Epic epic left join UserProject userProject
        on epic.project.id=userProject.project.id
        where epic.id = :epic_id and epic.deleted = false and userProject.user.id = :user_id
        """)
    boolean checkMembership(@Param("epic_id") long epicId, @Param("user_id") long userId);

    @Modifying(clearAutomatically = true)
    @Query("""
        update Epic epic
        set epic.deleted = true
        where epic.deleted = false and epic.project.id = :projectId
        """)
    int deleteAllByProjectId(@Param("projectId") long projectId);

    /*
    Hibernate에서 생성한 Query를 실행해보았을 때 쿼리 자체는 정상이나
    이유는 모르겠는데 nested projection이 제대로 안 먹힘...
    -> 어쩔 수 없이 쪼개서 진행..
     */
    // @Query("""
    //     select epic.id as id,
    //     epic.name as name,
    //     epic.startDate as startDate,
    //     epic.endDate as endDate,
    //     task.id as taskId,
    //     task.name as taskName,
    //     task.startDate as taskStartDate,
    //     task.endDate as taskEndDate
    //     from Epic epic left join fetch Task task on epic.id = task.epic.id
    //     where epic.project.id = :project_id and epic.deleted = false and task.deleted = false
    //     and ((task.startDate between :start_date and :end_date) or (task.endDate between :start_date and :end_date))
    //     """)
    // List<EpicProjectionData> selectProjectEpicsWithDateRange(@Param("project_id") long project_id, @Param("start_date")
    // LocalDate start_date, @Param("end_date") LocalDate end_date);

    /**
     * Retrieves a list of project epics with tasks that fall within a specified date range.
     *
     * @param project_id The ID of the project.
     * @param start_date The start date of the date range.
     * @param end_date   The end date of the date range.
     * @return A list of EpicProjectionDataItem objects that match the search criteria.
     */
    @Query("""
        select epic.id as epicId,
        epic.name as epicName,
        epic.startDate as epicStartDate,
        epic.endDate as epicEndDate,
        task.id as taskId,
        task.name as taskName,
        task.startDate as taskStartDate,
        task.endDate as taskEndDate,
        coalesce(task.status, "TODO") as taskStatus,
        task.user.id as userId
        from Epic epic left outer join Task task on epic.id = task.epic.id
        where epic.project.id = :project_id and epic.deleted = false
        and
         (
          (task.startDate between :start_date and :end_date)
          or (task.endDate between :start_date and :end_date)
          or (task.startDate is null and task.endDate is null)
         )
        """)
    List<EpicProjectionDataItem> selectProjectEpicsWithDateRange(@Param("project_id") long project_id,
        @Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date);

    @Query("""
        select epic.project.id
        from Epic epic
        where epic.deleted = false and epic.id = :epicId
        """)
    Optional<Long> findProjectIdByEpicId(@Param("epicId") long epicId);
}
