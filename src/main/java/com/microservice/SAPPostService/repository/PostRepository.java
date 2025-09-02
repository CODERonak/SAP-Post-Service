package com.microservice.SAPPostService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.SAPPostService.model.Post;

/*
 * This is the repository interface for the users table in the database.
 * It has the folowing methods:
 * - existsByAuthorId: check if a user exists by authorId: find a user by email
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByUserId(Long userId);
}