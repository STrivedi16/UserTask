package com.example.Users.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Users.entity.RolePermissionEntity;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Integer> {

}
