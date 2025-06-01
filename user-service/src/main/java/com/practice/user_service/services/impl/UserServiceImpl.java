package com.practice.user_service.services.impl;

import com.practice.user_service.entities.Role;
import com.practice.user_service.entities.User;
import com.practice.user_service.repositories.RoleRepository;
import com.practice.user_service.repositories.UserRepository;
import com.practice.user_service.requests.UserRequest;
import com.practice.user_service.responses.UserResponse;
import com.practice.user_service.services.interfaces.UserService;
import com.practice.user_service.util.GenericModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final GenericModelMapper genericModelMapper;

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        log.info("Creating user with request: {}", userRequest);
        User user = genericModelMapper.convertToEntity(userRequest, User.class);
        user.getAddress().forEach(address -> {
            address.setUser(user);
            address.setCreatedOn(LocalDateTime.now());
        });
        List<Role> roles = roleRepository.findByNameIn(userRequest.getRole());
        user.setRole(roles);
        user.setCreatedOn(LocalDateTime.now());
        User saved = userRepository.save(user);
        return genericModelMapper.convertToDto(saved, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        log.info("Updating user with ID: {}", userId);
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        existingUser.setFirstName(userRequest.getFirstName());
        existingUser.setLastName(userRequest.getLastName());
        existingUser.setUsername(userRequest.getUsername());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPassword(userRequest.getPassword());
        existingUser.setGender(userRequest.getGender());
        existingUser.setMobileNumber(userRequest.getMobileNumber());
        List<Role> roles = roleRepository.findByNameIn(userRequest.getRole());
        existingUser.setRole(roles);
        User updatedUser = userRepository.save(existingUser);
        return genericModelMapper.convertToDto(updatedUser, UserResponse.class);
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Deleting user with ID: {}", userId);
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        userRepository.delete(existingUser);
        log.info("User with ID: {} deleted successfully", userId);
    }

    @Override
    public UserResponse getUserById(Long userId) {
        log.info("Fetching user with ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return genericModelMapper.convertToDto(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        log.info("Fetching user with email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return genericModelMapper.convertToDto(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Fetching all users");
        List<User> users = userRepository.findAll();
        if (users != null && !users.isEmpty()) {
            return genericModelMapper.convertToDtoList(users, UserResponse.class);
        }
        return List.of();
    }
}
