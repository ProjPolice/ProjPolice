package com.projpolice.domain.project.domain.rdb;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.projpolice.domain.user.domain.User;
import com.projpolice.global.common.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a UserProject entity.
 *
 * This class is annotated with JPA annotations for database mapping. It extends the BaseEntity class.
 * It contains two instance variables, user and project, which are both of type User and Project respectively.
 * The user and project variables are required and are mapped to foreign key columns in the database.
 * Additionally, this class defines a unique constraint on the user_id and project_id columns in the user_project table.
 *
 * @see BaseEntity
 * @see User
 * @see Project
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_project", uniqueConstraints = {
    @UniqueConstraint(
        name = "uk_user_project",
        columnNames = {"user_id", "project_id"}
    )
})
@SQLDelete(sql = "UPDATE user_project SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class UserProject extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_project_user"))
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "fk_user_project_project"))
    private Project project;
}
