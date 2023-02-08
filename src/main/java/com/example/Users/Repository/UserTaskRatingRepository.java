package com.example.Users.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Users.entity.UserTaskRatingEntity;

@Repository
public interface UserTaskRatingRepository extends JpaRepository<UserTaskRatingEntity, Integer> {

}
