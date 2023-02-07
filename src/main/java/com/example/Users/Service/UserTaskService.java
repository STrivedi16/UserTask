package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Repository.TaskRepository;
import com.example.Users.Repository.UserTaskRepo;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.Tasks;
import com.example.Users.entity.Users;
import com.example.Users.entity.UsersTaskEntity;
import com.example.Users.entity.UsertaskDTO;

@Service
public class UserTaskService {

	@Autowired
	private UserTaskRepo userTaskRepo;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private TaskRepository taskRepository;

	public UsersTaskEntity addtaskuser(UsertaskDTO dto) throws Exception {
		Users users = this.usersRepository.findById(dto.getUserid()).orElseThrow(() -> new Exception("User not Found"));

		Tasks tasks = this.taskRepository.findById(dto.getTaskid()).orElseThrow(() -> new Exception("Task not Found"));

		UsersTaskEntity entity = new UsersTaskEntity();

		entity.setUser(users);

		entity.setTasks(tasks);

		entity.setStatus(dto.getStatus());

		return this.userTaskRepo.save(entity);
	}

	public UsersTaskEntity updatestatus(int id, UsertaskDTO dto) throws Exception {

		UsersTaskEntity entity = this.userTaskRepo.findById(id).orElseThrow(() -> new Exception("Data not get"));
		entity.setStatus(dto.getStatus());
		return this.userTaskRepo.save(entity);

	}

}
