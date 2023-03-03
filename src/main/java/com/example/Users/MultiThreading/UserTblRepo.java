package com.example.Users.MultiThreading;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTblRepo extends JpaRepository<UserTbl, Integer> {

}
