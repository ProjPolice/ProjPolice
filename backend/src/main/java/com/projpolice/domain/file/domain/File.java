package com.projpolice.domain.file.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.projpolice.global.common.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File extends BaseEntity {

    @NotNull
    @Column
    @Size(max = 255)
    private String name;

    @Column
    @Size(max = 255)
    private String comment;

    @Column(length = 255)
    private String uuid;

    private Integer version;

    @NotNull
    @Size(max = 25)
    private String extension;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
