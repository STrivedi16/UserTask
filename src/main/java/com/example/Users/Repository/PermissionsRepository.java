package com.example.Users.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Users.entity.Permissions;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Integer> {

}
