package com.microservice.SAPPostService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.SAPPostService.client.UserClient;
import com.microservice.SAPPostService.dto.*;
import com.microservice.SAPPostService.exceptions.custom.NotFoundException;
import com.microservice.SAPPostService.mapper.PostMapper;
import com.microservice.SAPPostService.model.Post;
import com.microservice.SAPPostService.repository.PostRepository;

import feign.FeignException;
import lombok.AllArgsConstructor;

/*
 * This is the service class for the posts table in the database.
 * It has the folowing methods:
 * - createPost: create a new post
 * - deletePost: delete a post
 * - getPostsByUserId: get all posts by userId
 */

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final PostMapper mapper;
    private final UserClient client;

    public PostResponse createPost(PostRequest request) {

        UserResponse user = null;

        try {
            user = client.getUserById(request.getUserId());
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("User with id " + request.getUserId() + " not found");
        }

        if (user == null)
            throw new NotFoundException("User not found");

        Post post = mapper.toModel(request);
        Post saved = repository.save(post);
        return mapper.toResponse(saved);
    }

    public PostResponse getPostById(Long postId) {
        Post post = repository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));
        return mapper.toResponse(post);
    }

    public void deletePost(Long postId) {
        if (repository.existsById(postId)) {
            repository.deleteById(postId);
        } else {
            throw new NotFoundException("Post not found");
        }
    }

    public List<PostResponse> getPostsByUserId(Long userId) {

        try {
            client.getUserById(userId);
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("User with id " + userId + " not found");
        }

        List<Post> posts = repository.findByUserId(userId);
        return mapper.toResponseList(posts);
    }

}