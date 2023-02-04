package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.Users;

@Service
public class UsersService {

	@Autowired
	private UsersRepository repository;

	public Users Register(Users users) throws Exception {
		Users users2 = this.repository.findByEmailIgnoreCase(users.getEmail());

		if (users2 != null)
			throw new Exception("UserData  Alrady Stored");

		return this.repository.save(users);
	}

}
