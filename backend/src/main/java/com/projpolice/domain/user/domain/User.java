package com.projpolice.domain.user.domain;

import com.projpolice.global.common.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
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
public class User extends BaseEntity {
    @NotNull
    private String email;
    // TODO: implement Entity and Hashcode/Equals
}
