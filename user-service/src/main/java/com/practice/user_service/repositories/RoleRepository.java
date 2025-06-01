package com.practice.user_service.repositories;

import com.practice.user_service.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    List<Role> findByNameIn(List<String> name);

    boolean existsByName(String name);
}
