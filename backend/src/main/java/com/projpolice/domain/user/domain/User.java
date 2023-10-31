package com.projpolice.domain.user.domain;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.projpolice.global.common.base.BaseEntity;

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
@SQLDelete(sql = "UPDATE `user` SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class User extends BaseEntity {

    @NotNull
    private String email;

    @Size(max = 25)
    @NotNull
    private String name;

    // TODO: implement
}
