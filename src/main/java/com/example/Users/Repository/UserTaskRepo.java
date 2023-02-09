package com.example.Users.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Users.entity.UsersTaskEntity;

@Repository
public interface UserTaskRepo extends JpaRepository<UsersTaskEntity, Integer> {

	UsersTaskEntity findById(UsersTaskEntity usertask);

}
