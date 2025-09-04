package com.microservice.SAPPostService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.SAPPostService.dto.*;
import com.microservice.SAPPostService.service.PostService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

/*
 * This is the controller class for the posts table in the database.
 * It has the folowing methods:
 * - createPost: create a new post
 * - getPostById: get post by id
 * - deletePost: delete a post
 * - getAllPostsByUserId: get all posts by userId
 */

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private final PostService service;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody @Valid PostRequest request) {
        PostResponse response = service.createPost(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        PostResponse response = service.getPostById(postId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        service.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponse>> getAllPostsByUserId(@PathVariable Long userId) {
        List<PostResponse> responseList = service.getPostsByUserId(userId);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

}