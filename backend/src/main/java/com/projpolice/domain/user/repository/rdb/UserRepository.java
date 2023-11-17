package com.projpolice.domain.user.repository.rdb;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projpolice.domain.user.domain.rdb.User;

/**
 * UserRepository is an interface that extends the JpaRepository interface.
 * It represents a repository for managing User entities in the application.
 *
 * It provides methods for retrieving, saving, updating, and deleting User entities
 * from the underlying database.
 *
 * This interface also defines an additional method findByEmail, which can be used
 * to find a user by their email address.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
