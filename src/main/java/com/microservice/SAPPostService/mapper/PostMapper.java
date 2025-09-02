package com.microservice.SAPPostService.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.microservice.SAPPostService.dto.*;
import com.microservice.SAPPostService.model.Post;

/*
 * This is the mapper interface for the posts table in the database.
 * It has the folowing methods:
 * - toModel: convert PostRequest to Post
 * - toResponse: convert Post to PostResponse
 * - toResponseList: convert List<Post> to List<PostResponse>
 */

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "postId", ignore = true)
    Post toModel(PostRequest postRequest);

    PostResponse toResponse(Post post);

    List<PostResponse> toResponseList(List<Post> posts);
}