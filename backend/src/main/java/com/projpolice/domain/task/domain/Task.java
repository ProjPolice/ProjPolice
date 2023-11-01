package com.projpolice.domain.task.domain;

import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.projpolice.domain.epic.domain.Epic;
import com.projpolice.domain.user.domain.User;
import com.projpolice.global.common.base.BaseEntity;
import com.projpolice.global.common.meta.converter.TaskStatusConverter;
import com.projpolice.global.common.meta.domain.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a User entity.
 * Inherits from the BaseEntity class and contains information about a user.
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE task SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Task extends BaseEntity {
    @NotNull
    @Size(max = 25)
    private String name;

    @Size(max = 255)
    private String description;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    // todo: 값 잘 들어있는지 확인 필요. 아니면 Converter/Enum 수정 필요.
    @NotNull
    @Convert(converter = TaskStatusConverter.class)
    private TaskStatus status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_task_to_user_user_id"))
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    // @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "epic_id", foreignKey = @ForeignKey(name = "fk_task_to_epic_epic_id"))
    private Epic epic;
}
