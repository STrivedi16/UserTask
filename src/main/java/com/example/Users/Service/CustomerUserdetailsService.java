package com.example.Users.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.Users;

@Service
public class CustomerUserdetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {

			Users users = this.repository.findByEmailIgnoreCase(username);

			return new User(users.getEmail(), users.getPassword(), new ArrayList<>());

		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User not Found or invlid email");
		}

	}

}
