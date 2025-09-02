package com.microservice.SAPPostService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
 * This is the DTO class for the posts table in the database.
 * It has the following fields:
 * - authorId: author id a foreign key to the users table
 * - title: the title of the post
 * - content: the content of the post
 */

@Data
public class PostRequest {
    @NotNull
    private Long userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}