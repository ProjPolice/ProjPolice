package com.projpolice.domain.epic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.epic.domain.Epic;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Long> {
    @Query("""
        select count(epic.id)>0
        from Epic epic left join UserProject userProject
        on epic.project.id=userProject.project.id
        where epic.id = :epic_id and epic.deleted = false and userProject.user.id = :user_id
        """)
    boolean checkMembership(@Param("epic_id") long epicId, @Param("user_id") long userId);
}
