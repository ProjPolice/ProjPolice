package com.projpolice.global.common.base;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

/**
 * This class serves as a base class for all entities in the system.
 * It provides common properties and functionality that can be inherited
 * by other entity classes.
 *
 * The BaseEntity class is annotated with the @Data and @MappedSuperclass annotations.
 *
 * The @Data annotation is from the Lombok library and automatically generates the
 * appropriate getters, setters, toString, equals, and hashCode methods for the
 * fields in this class.
 *
 * The @MappedSuperclass annotation is from the JPA (Java Persistence API) and
 * indicates that this class should be treated as a superclass for entity classes.
 *
 * The BaseEntity class has a single field:
 * - id: This field represents the unique identifier of an entity. It is annotated
 *        with @Id, indicating that it is the primary key for the entity, and
 *        @GeneratedValue(strategy = GenerationType.IDENTITY), indicating that
 *        the value for this field will be generated automatically by the database
 *        using an identity-based strategy.
 *
 * Note: This class does not contain any example code, author, or version tags as
 * per the instructions.
 */
@Data
@MappedSuperclass
abstract public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
