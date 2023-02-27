package com.example.Users.FileHandle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer> {

//	@Query(value = "select * from file_entity fe where fe.name= :filename", nativeQuery = true)
//	FileEntity findByNameIgnoreCase(@Param("filename") String filename);
	
	
	@Query(value = "select * from file_entity where name= :file",nativeQuery = true)
	FileEntity findByNameIgnoreCase(@Param("file")  String filename);
	
	@Query(value = "select * from file_entity where id= :fileid",nativeQuery = true)
	FileEntity findById(@Param("fileid") int id);
}
