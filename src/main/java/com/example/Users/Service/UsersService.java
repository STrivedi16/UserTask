package com.example.Users.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.Users.DTO.UserDto;
import com.example.Users.Interface.UserInterface;
import com.example.Users.Interface.UserTaskReview;
import com.example.Users.Interface.UsersPermission;
import com.example.Users.Interface.UsersTask;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.Users;

@Service
public class UsersService {

	@Autowired
	private UsersRepository repository;

	//private static final String HASH_KEY = "Users";

	public Users register(UserDto users) throws Exception {
		Users usersdata = this.repository.findByEmailIgnoreCase(users.getEmail());

		if (usersdata != null)
			throw new Exception("user data  already stored");

		Users newusers = new Users();
		newusers.setName(users.getName());
		newusers.setEmail(users.getEmail());
		newusers.setPassword(users.getPassword());
		newusers.setAdd(users.getAddress());
		newusers.setCity(users.getCity());

		return this.repository.save(newusers);
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
		return this.repository.findById(id).orElseThrow(() -> new Exception("user not found"));
	}

	public List<UserInterface> getAll(Integer pagesize, Integer pagenumber) {
		
		Pageable pageable=PageRequest.of(pagenumber, pagesize);
		
		Page<UserInterface> page=this.repository.findAll(pageable,UserInterface.class);
		
		
		
		return page.getContent();
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

	public List<UserTaskReview> showTaskReviewForAdmin(int id) {
		return this.repository.findByID(id, UserTaskReview.class);
	}
	


}
