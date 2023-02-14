package com.example.Users.FileHandle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserComentRepository extends JpaRepository<UserComent, Integer> {

}
