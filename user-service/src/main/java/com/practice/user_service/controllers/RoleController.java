package com.practice.user_service.controllers;

import com.practice.user_service.requests.RoleRequest;
import com.practice.user_service.responses.ApiResponse;
import com.practice.user_service.responses.RoleResponse;
import com.practice.user_service.services.interfaces.RoleService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@GraphQLApi
@RequiredArgsConstructor
@Slf4j
public class RoleController {

    private final RoleService roleService;

    @GraphQLQuery(name = "createRole", description = "Create a new role")
    public ApiResponse<RoleResponse> createRole(@GraphQLArgument(name = "request") RoleRequest request) {

        log.info("Creating role with request: {}", request);

        try {
            return new ApiResponse<>(HttpStatus.SC_OK, "Role created successfully", roleService.createRole(request));
        } catch (Exception e) {
            log.error("Error creating role: {}", e.getMessage(), e);
            return new ApiResponse<>(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Error creating role: " + e.getMessage(), null);
        }

    }
    @GraphQLQuery(name = "updateRole", description = "Delete a role by ID")
    public ApiResponse<RoleResponse>updateRole(@GraphQLArgument(name = "roleId") Long roleId, @GraphQLArgument(name = "request") RoleRequest request) {

        log.info("Updating role with ID: {} and request: {}", roleId, request);

        try {
            return new ApiResponse<>(HttpStatus.SC_OK, "Role updated successfully", roleService.updateRole(roleId, request));
        } catch (Exception e) {
            log.error("Error updating role: {}", e.getMessage(), e);
            return new ApiResponse<>(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Error updating role: " + e.getMessage(), null);
        }

    }
    @GraphQLQuery(name = "deleteRole", description = "Delete a role by ID")
    public ApiResponse<Void> deleteRole(@GraphQLArgument(name = "roleId") Long roleId) {

        log.info("Deleting role with ID: {}", roleId);

        try {
            roleService.deleteRole(roleId);
            return new ApiResponse<>(HttpStatus.SC_OK, "Role deleted successfully", null);
        } catch (Exception e) {
            log.error("Error deleting role: {}", e.getMessage(), e);
            return new ApiResponse<>(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Error deleting role: " + e.getMessage(), null);
        }

    }

    @GraphQLQuery(name = "getRoleById", description = "Get role by ID")
    public ApiResponse<RoleResponse> getRoleById(@GraphQLArgument(name = "roleId") Long roleId) {

        log.info("Fetching role with ID: {}", roleId);

        try {
            return new ApiResponse<>(HttpStatus.SC_OK, "Role fetched successfully", roleService.getRoleById(roleId));
        } catch (Exception e) {
            log.error("Error fetching role by ID: {}", e.getMessage(), e);
            return new ApiResponse<>(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Error fetching role by ID: " + e.getMessage(), null);
        }

    }
    @GraphQLQuery(name = "getAllRoles", description = "Get all roles")
    public ApiResponse<List<RoleResponse>> getAllRoles() {

        log.info("Fetching all roles");

        try {
            return new ApiResponse<>(HttpStatus.SC_OK, "Role created successfully", roleService.getAllRoles());
        } catch (Exception e) {
            log.error("Error fetching all roles: {}", e.getMessage(), e);
            return new ApiResponse<>(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Error fetching all roles: " + e.getMessage(), null);
        }

    }
    @GraphQLQuery(name = "getRoleByName", description = "Get role by name")
    public ApiResponse<RoleResponse> getRoleByName(@GraphQLArgument(name = "roleName") String roleName) {

        log.info("Fetching role with name: {}", roleName);

        try {
            return new ApiResponse<>(HttpStatus.SC_OK, "Role fetched successfully", roleService.getRoleByName(roleName));
        } catch (Exception e) {
            log.error("Error fetching role by name: {}", e.getMessage(), e);
            return new ApiResponse<>(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Error fetching role by name: " + e.getMessage(), null);
        }

    }
}
