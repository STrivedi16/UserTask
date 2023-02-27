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
import com.example.Users.entity.TempUserDto;
import com.example.Users.entity.Users;

@Service
public class UsersService {

	@Autowired
	private UsersRepository repository;

	//private static final String HASH_KEY = "Users";

	public TempUserDto register(TempUserDto d) throws Exception {
		
		System.err.println(d.getEmail());
		System.err.println("way to get email");
		
		Users usersdata = this.repository.findByEmailIgnoreCase(d.getEmail());
		
		System.err.println(usersdata );
		

		if (usersdata != null)
			throw new Exception("user data  already stored");

		Users newusers = new Users();
		newusers.setName(d.getName());
		newusers.setEmail(d.getEmail());
		newusers.setPassword(d.getPassword());
		newusers.setAdd(d.getAddress());
		newusers.setCity(d.getCity());
		
		this.repository.save(newusers);

		return d;
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
