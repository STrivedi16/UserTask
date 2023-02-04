package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Repository.TaskRepository;
import com.example.Users.entity.Tasks;

@Service
public class TasksService {

	@Autowired
	private TaskRepository repository;

	public Tasks setTask(Tasks tasks) {
		return this.repository.save(tasks);
	}

}
