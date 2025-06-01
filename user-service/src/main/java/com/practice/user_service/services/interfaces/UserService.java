package com.practice.user_service.services.interfaces;

import com.practice.user_service.requests.UserRequest;
import com.practice.user_service.responses.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long userId, UserRequest userRequest);
    void deleteUser(Long userId);
    UserResponse getUserById(Long userId);
    UserResponse getUserByEmail(String email);
    List<UserResponse>getAllUsers();
}
