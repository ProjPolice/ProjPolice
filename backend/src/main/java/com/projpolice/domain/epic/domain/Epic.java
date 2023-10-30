package com.projpolice.domain.epic.domain;

import java.time.LocalDate;

import com.projpolice.domain.project.domain.Project;
import com.projpolice.global.common.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Epic extends BaseEntity {

    @Size(max = 25)
    @NotNull
    @Column
    private String name;

    @Size(max = 255)
    @Column
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "fk_project_epic"))
    private Project project;
}
