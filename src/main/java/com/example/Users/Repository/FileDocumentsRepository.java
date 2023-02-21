package com.example.Users.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Users.entity.FileDocumentsEntity;
@Repository
public interface FileDocumentsRepository extends JpaRepository<FileDocumentsEntity, Integer> {

}
