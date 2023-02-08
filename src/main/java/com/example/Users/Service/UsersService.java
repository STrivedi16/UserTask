package com.example.Users.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.Users.Interface.UsersPermission;
import com.example.Users.Interface.UsersTask;
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

	public ArrayList<SimpleGrantedAuthority> getAuthorities(int id) {
		ArrayList<SimpleGrantedAuthority> auth = new ArrayList<>();

		if (id + "permissions" != null) {
			ArrayList<SimpleGrantedAuthority> auth1 = new ArrayList<>();

			List<UsersPermission> list = this.repository.findById(id, UsersPermission.class);

			System.out.println(list);

			list.forEach(e -> {
				System.out.println(e);
				auth1.add(new SimpleGrantedAuthority(e.getPermissions()));
			});

			auth = auth1;
		}
		return auth;
	}

	public Users getbyid(int id) throws Exception {
		return this.repository.findById(id).orElseThrow(() -> new Exception("user Not Found"));
	}

	public List<Users> getall() {
		return this.repository.findAll();
	}

	public List<UsersTask> getusertask(int id) {
		return this.repository.findByid(id, UsersTask.class);
	}

	public List<UsersTask> showusertask(int id) {
		return this.repository.findByid(id, UsersTask.class);
	}

}
