package com.practice.user_service.services.impl;

import com.practice.user_service.entities.Role;
import com.practice.user_service.repositories.RoleRepository;
import com.practice.user_service.requests.RoleRequest;
import com.practice.user_service.responses.RoleResponse;
import com.practice.user_service.services.interfaces.RoleService;
import com.practice.user_service.util.GenericModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final GenericModelMapper genericModelMapper;

    @Override
    public RoleResponse createRole(RoleRequest request) {
        Role role = genericModelMapper.convertToEntity(request, Role.class);
        role.setCreatedOn(LocalDateTime.now());
        role = roleRepository.save(role);
        return genericModelMapper.convertToDto(role, RoleResponse.class);

    }

    @Override
    public RoleResponse getRoleById(Long roleId) {
    Role role = roleRepository.findById(roleId).orElse(null);
    if (role != null) {
            return genericModelMapper.convertToDto(role, RoleResponse.class);
        }
        return null;
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        if (!roles.isEmpty()) {
            return genericModelMapper.convertToDtoList(roles, RoleResponse.class);
        }
        return List.of();
    }

    @Override
    public RoleResponse updateRole(Long roleId, RoleRequest request) {
    Role existingRole = roleRepository.findById(roleId).orElse(null);
    if (existingRole != null) {
            existingRole.setName(request.getName());
            existingRole.setDescription(request.getDescription());
            existingRole.setUpdatedOn(LocalDateTime.now());
            existingRole = roleRepository.save(existingRole);
            return genericModelMapper.convertToDto(existingRole, RoleResponse.class);
        }
        return null;
    }

    @Override
    public void deleteRole(Long roleId) {
        if (roleRepository.existsById(roleId)) {
            roleRepository.deleteById(roleId);
        } else {
            throw new IllegalArgumentException("Role with ID " + roleId + " does not exist.");
        }

    }

    @Override
    public RoleResponse getRoleByName(String roleName) {
    Role role = roleRepository.findByName(roleName);
    if (role != null) {
            return genericModelMapper.convertToDto(role, RoleResponse.class);
        }
        return null;
    }
}
