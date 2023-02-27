package com.example.Users.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Users.entity.TempUserDto;

@Repository
public interface TempUserTableRepository extends JpaRepository<TempUserDto, Integer>{

	TempUserDto findByEmailIgnoreCase(String email);
	
}
