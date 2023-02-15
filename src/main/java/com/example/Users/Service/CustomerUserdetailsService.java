package com.example.Users.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.Users.Repository.UsersRepository;
import com.example.Users.Responce.ResourceNotFoundException;
import com.example.Users.entity.Users;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository repository;

	@Autowired
	private UsersService service;

	@Override
	public UserDetails loadUserByUsername(String username) {

		if (username.isEmpty()) {
			throw new NullPointerException();
		} else {

			try {

				Users users = this.repository.findByEmailIgnoreCase(username);
				if (users != null) {

					ArrayList<SimpleGrantedAuthority> arrayList = this.service.getAuthorities(users.getId());

					return new User(users.getEmail(), users.getPassword(), arrayList);

				}

				else {
					throw new ResourceNotFoundException();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ResourceNotFoundException();
			}

		}

	}

}
