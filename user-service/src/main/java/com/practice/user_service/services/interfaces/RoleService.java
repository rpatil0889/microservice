package com.practice.user_service.services.interfaces;

import com.practice.user_service.requests.RoleRequest;
import com.practice.user_service.responses.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse createRole(RoleRequest request);
    RoleResponse getRoleById(Long roleId);
    List<RoleResponse> getAllRoles();
    RoleResponse updateRole(Long roleId, RoleRequest request);
    void deleteRole(Long roleId);
    RoleResponse getRoleByName(String roleName);
}
