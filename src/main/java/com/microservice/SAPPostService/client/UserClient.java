package com.microservice.SAPPostService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.SAPPostService.dto.UserResponse;

/*
 * This is the client interface for the users table in the database.
 * It has the folowing method:
 * - getUserById: get user by id
 */

@FeignClient(name = "SAP-User-Service", url = "${USER_SERVICE}")
public interface UserClient {
   @GetMapping("api/users/{userId}")
   UserResponse getUserById(@PathVariable("userId") Long userId);
}