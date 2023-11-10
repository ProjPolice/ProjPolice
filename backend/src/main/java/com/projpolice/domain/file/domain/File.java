package com.projpolice.domain.file.domain;

import java.time.LocalDateTime;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import com.projpolice.domain.file.request.FileUploadRequest;
import com.projpolice.domain.task.domain.rdb.Task;
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

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE file SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class File extends BaseEntity {
    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String comment;

    @Size(max = 255)
    private String uuid;

    private int version;

    @NotNull
    @Size(max = 25)
    private String extension;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "fk_file_to_task_task_id"))
    private Task task;

    public static File of(FileUploadRequest fileUploadRequest, Task task, String uuid) {
        return File.builder()
            .name(FilenameUtils.getBaseName(fileUploadRequest.getFile().getOriginalFilename()))
            .comment(fileUploadRequest.getComment())
            .uuid(uuid)
            .version(fileUploadRequest.getVersion())
            .extension(StringUtils.getFilenameExtension(fileUploadRequest.getFile().getOriginalFilename()))
            .task(task)
            .build();
    }
}
