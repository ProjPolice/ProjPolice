package com.projpolice.domain.user.domain;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.projpolice.global.common.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Size(max = 50)
    private String email;

    @NotNull
    @Size(max = 25)
    private String name;

    @NotNull
    @Size(max = 50)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
