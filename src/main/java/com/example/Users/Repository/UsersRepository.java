package com.example.Users.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Users.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	Users findByEmailIgnoreCase(String email);

}
