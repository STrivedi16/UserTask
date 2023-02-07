package com.example.Users.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

	@Autowired
	private UsersService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {

			Users users = this.repository.findByEmailIgnoreCase(username);

			ArrayList<SimpleGrantedAuthority> arrayList = this.service.getAuthorities(users.getId());

			System.out.println("All permissions" + arrayList);

			return new User(users.getEmail(), users.getPassword(), arrayList);

		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User not Found or invlid email");
		}

	}

}
