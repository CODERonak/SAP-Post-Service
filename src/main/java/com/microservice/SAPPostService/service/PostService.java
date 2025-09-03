package com.microservice.SAPPostService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.SAPPostService.client.UserClient;
import com.microservice.SAPPostService.dto.*;
import com.microservice.SAPPostService.mapper.PostMapper;
import com.microservice.SAPPostService.model.Post;
import com.microservice.SAPPostService.repository.PostRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final PostMapper mapper;
    private final UserClient client;

    public PostResponse createPost(Long userId, PostRequest request) {
        UserResponse user = client.getUserById(userId);

        if (user == null)
            throw new RuntimeException("User not found");

        Post post = mapper.toModel(request);
        post.setUserId(userId);
        Post saved = repository.save(post);
        return mapper.toResponse(saved);
    }

    public void deletePost(Long postId) {
        if (repository.existsById(postId)) {
            repository.deleteById(postId);
        } else {
            throw new RuntimeException("Post not found");
        }
    }

    public List<PostResponse> getPostsByUserId(Long userId) {

        UserResponse user = client.getUserById(userId);

        if (user == null)
            throw new RuntimeException("User not found");

        List<Post> posts = repository.findByUserId(userId);
        return mapper.toResponseList(posts);
    }

}
