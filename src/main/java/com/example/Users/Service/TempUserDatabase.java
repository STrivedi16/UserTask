package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.DTO.UserDto;
import com.example.Users.Repository.TempUserTableRepository;
import com.example.Users.entity.TempUserDto;



@Service
public class TempUserDatabase {

	@Autowired
	private TempUserTableRepository repository;
	
	
	public TempUserDto dto(UserDto  dto)
	{
		TempUserDto userDto=new TempUserDto();
		userDto.setName(dto.getName());
		userDto.setEmail(dto.getEmail());
		userDto.setAddress(dto.getAddress());
		userDto.setCity(dto.getCity());
		userDto.setPassword(dto.getPassword());
		
		return this.repository.save(userDto);
	}
	
	public TempUserDto getData(String mail)
	{
		return this.repository.findByEmailIgnoreCase(mail);
	}
	
	

	public TempUserDto clearDto(String email)
	{
		
		TempUserDto userDto=this.repository.findByEmailIgnoreCase(email);
		
		
		userDto.setName(null);
		userDto.setEmail(null);
		userDto.setAddress(null);
		userDto.setCity(null);
		userDto.setPassword(null);
		
		return this.repository.save(userDto);
	}
}
