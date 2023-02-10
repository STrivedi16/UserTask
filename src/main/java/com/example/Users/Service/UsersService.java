package com.example.Users.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.Users.Interface.UserTaskReview;
import com.example.Users.Interface.UsersPermission;
import com.example.Users.Interface.UsersTask;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.Users;

@Service
public class UsersService {

	@Autowired
	private UsersRepository repository;

	public Users register(Users users) throws Exception {
		Users users2 = this.repository.findByEmailIgnoreCase(users.getEmail());

		if (users2 != null)
			throw new Exception("User data  already stored");

		return this.repository.save(users);
	}

	public ArrayList<SimpleGrantedAuthority> getAuthorities(int id) {
		ArrayList<SimpleGrantedAuthority> auth = new ArrayList<>();

		if (id + "permissions" != null) {
			ArrayList<SimpleGrantedAuthority> auth1 = new ArrayList<>();

			List<UsersPermission> list = this.repository.findById(id, UsersPermission.class);

			list.forEach(e -> {

				auth1.add(new SimpleGrantedAuthority(e.getPermissions()));
			});

			auth = auth1;
		}
		return auth;
	}

	public Users getById(int id) throws Exception {
		return this.repository.findById(id).orElseThrow(() -> new Exception("user Not Found"));
	}

	public List<Users> getAll() {
		return this.repository.findAll();
	}

	public List<UsersTask> getUserTask(int id) {
		return this.repository.findByid(id, UsersTask.class);
	}

	public List<UsersTask> showUserTask(int id) {
		return this.repository.findByid(id, UsersTask.class);
	}

	public List<UserTaskReview> showTaskReview(int id) {
		return this.repository.findByID(id, UserTaskReview.class);
	}

	public List<UserTaskReview> showTaskReviewFORADMIN(int id) {
		return this.repository.findByID(id, UserTaskReview.class);
	}

}
