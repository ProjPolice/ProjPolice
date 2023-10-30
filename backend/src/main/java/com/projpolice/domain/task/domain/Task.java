package com.projpolice.domain.task.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class Task extends BaseEntity {
	@NotNull
	@Size(max = 25)
	private String name;

	@Size(max = 255)
	private String description;

	@CreationTimestamp
	@ColumnDefault("CURRENT_TIMESTAMP")
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@ColumnDefault("CURRENT_TIMESTAMP")
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@NotNull
	@Convert(converter = TaskStatusConverter.class)
	private TaskStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_task_to_user_user_id"))
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "epic_id", foreignKey = @ForeignKey(name = "fk_task_to_epic_epic_id"))
	private Epic epic;
}
