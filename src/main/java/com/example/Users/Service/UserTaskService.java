package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Repository.TaskRepository;
import com.example.Users.Repository.UserTaskRepo;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.Status;
import com.example.Users.entity.Tasks;
import com.example.Users.entity.Users;
import com.example.Users.entity.UsersTaskEntity;
import com.example.Users.entity.UsertaskDto;

@Service
public class UserTaskService {

	@Autowired
	private UserTaskRepo userTaskRepo;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private TaskRepository taskRepository;

	public UsersTaskEntity addTaskUser(UsertaskDto dto) throws Exception {
		Users users = this.usersRepository.findById(dto.getUserid()).orElseThrow(() -> new Exception("User not Found"));

		Tasks tasks = this.taskRepository.findById(dto.getTaskid()).orElseThrow(() -> new Exception("Task not Found"));

		UsersTaskEntity entity = new UsersTaskEntity();

		entity.setUser(users);

		entity.setTasks(tasks);

		entity.setStatus(Status.TO_DO);

		return this.userTaskRepo.save(entity);
	}

	public UsersTaskEntity updateStatus(int id, UsertaskDto dto) throws Exception {

		UsersTaskEntity entity = this.userTaskRepo.findById(id).orElseThrow(() -> new Exception("Data not get"));

		entity.setStatus(dto.getStatus());

		return this.userTaskRepo.save(entity);

	}

//	public UsersTaskEntity Giverating(int id, UserTaskStatusDTO dto) throws Exception {
//
//		UsersTaskEntity entity = this.userTaskRepo.findById(id).orElseThrow(() -> new Exception("Data Not Get"));
//
//		if (entity.getStatus().equals(Status.DONE)) {
//			entity.setRating(dto.getRating());
//			entity.setRatedby(dto.getRateby());
//
//			return this.userTaskRepo.save(entity);
//		}
//		throw new Exception("No Data Found");
//
//	}

}
