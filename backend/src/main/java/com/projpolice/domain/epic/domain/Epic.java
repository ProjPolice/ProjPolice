package com.projpolice.domain.epic.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.projpolice.global.common.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Size(max=25)
    private String name;

    @Size(max=255)
    private String description;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // todo: 풀받고 돌리기
    // @NotNull
    // private Project project;
}
