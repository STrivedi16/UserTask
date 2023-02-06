package com.example.Users.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Users.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

}
