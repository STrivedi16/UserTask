package com.example.Users.Service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.Users.Redis.RedisService;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.Responce.ResourceNotFoundException;
import com.example.Users.entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository repository;

	@Autowired
	private UsersService service;

	@Autowired
	private RedisService redisService;

	@Override
	public UserDetails loadUserByUsername(String username) {

		System.err.println("andjknfsfbsfasfys");
		ArrayList<SimpleGrantedAuthority> arrayList = null;
		Users users = new Users();
		if (!redisService.isKeyExist(username, username)) {
			users = this.repository.findByEmailIgnoreCase(username);
			System.err.println("Hello");
			redisService.addInCache(username, username, users.toString());
		} else {

			String jsonString = (String) redisService.getFromCache(username, username);

			System.err.println("This is nrew");
			try {

				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(jsonString, Map.class);
				System.out.println(map.toString());
				users.setEmail((String) (map.get("email")));
				users.setPassword((String) map.get("password"));

				if (users != null) {

					arrayList = this.service.getAuthorities(users.getId());

				}

				else {
					throw new ResourceNotFoundException();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ResourceNotFoundException();
			}

		}
		return new User(users.getEmail(), users.getPassword(), arrayList);
	}

}
