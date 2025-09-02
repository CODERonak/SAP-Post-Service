package com.microservice.SAPPostService.dto;

import lombok.Data;

@Data
public class UserResponse {
   private Long userId;

   private String fullname;

   private String email;

   private String bio;
}