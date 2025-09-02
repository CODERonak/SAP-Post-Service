package com.microservice.SAPPostService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.SAPPostService.dto.UserResponse;

@FeignClient(name = "SAP-User-Service")
public interface UserClient {
   @GetMapping("api/users/{userId}")
   UserResponse getUserById(@PathVariable("userId") Long id);
}