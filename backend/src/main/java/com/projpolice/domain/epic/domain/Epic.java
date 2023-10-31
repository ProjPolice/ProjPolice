package com.projpolice.domain.epic.domain;

import java.time.LocalDate;

import com.projpolice.domain.project.domain.Project;
import com.projpolice.global.common.base.BaseEntity;

import jakarta.persistence.Column;
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
public class Epic extends BaseEntity {
	@NotNull
	@Size(max = 25)
	private String name;

	@Size(max = 255)
	private String description;

	@Column
	private LocalDate startDate;

	@Column
	private LocalDate endDate;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "fk_epic_to_project_project_id"))
	private Project project;
}
