package com.projpolice.domain.project.domain;

import java.time.LocalDate;

import com.projpolice.global.common.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a project entity.
 *
 * <p>The Project class is an entity class that represents a project in the application.
 * It extends the BaseEntity class, which provides common fields and methods for all entities.</p>
 *
 * <p>The Project class is annotated with the @Entity annotation, indicating that it is a
 * persistent entity that can be stored and retrieved in a database. It is also annotated with
 * the convenience annotations @Getter, @Builder, @NoArgsConstructor, and @AllArgsConstructor.</p>
 *
 * <p>The Project class has the following fields:</p>
 * <ul>
 *   <li><b>name:</b> A string representing the name of the project. It is required and has a maximum length of 25 characters.</li>
 *   <li><b>description:</b> An optional string representing the description of the project. It has a maximum length of 255 characters.</li>
 *   <li><b>startDate:</b> An optional LocalDate representing the start date of the project.</li>
 *   <li><b>endDate:</b> An optional LocalDate representing the end date of the project.</li>
 *   <li><b>image:</b> An optional string representing the image path of the project. It has a maximum length of 255 characters.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 * // Create a new project
 * Project project = Project.builder()
 *                          .name("Project A")
 *                          .description("This is a sample project.")
 *                          .startDate(LocalDate.now())
 *                          .endDate(LocalDate.of(2022, 12, 31))
 *                          .image("/path/to/image.jpg")
 *                          .build();
 *
 * // Save the project to the database
 * projectRepository.save(project);
 * </pre>
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseEntity {

    @Size(max = 25)
    @NotNull
    @Column
    private String name;

    @Size(max = 255)
    @Column
    private String description;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Size(max = 255)
    @Column
    private String image;
}
