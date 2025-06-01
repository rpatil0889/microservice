package com.practice.user_service.controllers;

import com.practice.user_service.requests.UserRequest;
import com.practice.user_service.responses.ApiResponse;
import com.practice.user_service.responses.UserResponse;
import com.practice.user_service.services.interfaces.UserService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@GraphQLApi
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GraphQLQuery(name = "createUser", description = "Create a new user")
    public ApiResponse<UserResponse> createUser(@GraphQLArgument UserRequest request) {
        log.info("Creating user with details: {}", request);
        try {
            UserResponse createdUser = userService.createUser(request);
            return ApiResponse.<UserResponse>builder()
                    .status(HttpStatus.SC_OK)
                    .message("User created successfully")
                    .data(createdUser)
                    .build();
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            return ApiResponse.<UserResponse>builder()
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .message("Failed to create user for email: "+request.getEmail())
                    .build();
        }
    }

    @GraphQLQuery(name = "updateUser", description = "Update an existing user")
    public ApiResponse<UserResponse> updateUser(@GraphQLArgument(name = "userId") Long userId, @GraphQLArgument(name = "request") UserRequest request) {
        log.info("Updating user with ID: {}", userId);
        try {
            UserResponse updatedUser = userService.updateUser(userId, request);
            return ApiResponse.<UserResponse>builder()
                    .status(HttpStatus.SC_OK)
                    .message("User updated successfully")
                    .data(updatedUser)
                    .build();
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());
            return ApiResponse.<UserResponse>builder()
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .message("Failed to update user: " + e.getMessage())
                    .build();
        }
    }

    @GraphQLQuery(name = "deleteUser", description = "Delete a user by ID")
    public ApiResponse<Void> deleteUser(@GraphQLArgument Long id) {
        log.info("Deleting user with ID: {}", id);
        try {
            userService.deleteUser(id);
            return ApiResponse.<Void>builder()
                    .status(HttpStatus.SC_OK)
                    .message("User deleted successfully")
                    .build();
        } catch (Exception e) {
            log.error("Error deleting user: {}", e.getMessage());
            return ApiResponse.<Void>builder()
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .message("Failed to delete user: " + e.getMessage())
                    .build();
        }
    }

    @GraphQLQuery(name = "getUserById", description = "Get a user by ID")
    public ApiResponse<UserResponse> getUserById(@GraphQLArgument Long id) {
        log.info("Fetching user with ID: {}", id);
        try {
            UserResponse user = userService.getUserById(id);
            if (user != null) {
                return ApiResponse.<UserResponse>builder()
                        .status(HttpStatus.SC_OK)
                        .message("User fetched successfully")
                        .data(user)
                        .build();
            } else {
                return ApiResponse.<UserResponse>builder()
                        .status(HttpStatus.SC_NOT_FOUND)
                        .message("User not found")
                        .build();
            }
        } catch (Exception e) {
            log.error("Error fetching user: {}", e.getMessage());
            return ApiResponse.<UserResponse>builder()
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .message("Failed to fetch user: " + e.getMessage())
                    .build();
        }
    }

    @GraphQLQuery(name = "getUserByEmail", description = "Get a user by email")
    public ApiResponse<UserResponse> getUserByEmail(@GraphQLArgument String email) {
        log.info("Fetching user with email: {}", email);
        try {
            UserResponse user = userService.getUserByEmail(email);
            if (user != null) {
                return ApiResponse.<UserResponse>builder()
                        .status(HttpStatus.SC_OK)
                        .message("User fetched successfully")
                        .data(user)
                        .build();
            } else {
                return ApiResponse.<UserResponse>builder()
                        .status(HttpStatus.SC_NOT_FOUND)
                        .message("User not found")
                        .build();
            }
        } catch (Exception e) {
            log.error("Error fetching user by email: {}", e.getMessage());
            return ApiResponse.<UserResponse>builder()
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .message("Failed to fetch user by email: " + e.getMessage())
                    .build();
        }
    }

    @GraphQLQuery(name = "getAllUsers", description = "Get all users")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        log.info("Fetching all users");
        try {
            List<UserResponse> users = userService.getAllUsers();
            return ApiResponse.<List<UserResponse>>builder()
                    .status(HttpStatus.SC_OK)
                    .message("Users fetched successfully")
                    .data(users)
                    .build();
        } catch (Exception e) {
            log.error("Error fetching all users: {}", e.getMessage());
            return ApiResponse.<List<UserResponse>>builder()
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .message("Failed to fetch users: " + e.getMessage())
                    .build();
        }
    }
}
