package com.microservice.SAPPostService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.SAPPostService.model.Post;

/*
 * This is the repository interface for the users table in the database.
 * It has the folowing methods:
 * - existsByAuthorId: check if a user exists by authorId
 * - List findByUserId: find all posts by userId
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByUserId(Long userId);

    List<Post> findByUserId(Long userId);
}