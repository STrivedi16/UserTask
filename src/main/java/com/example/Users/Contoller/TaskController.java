package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.Success;
import com.example.Users.Service.TasksService;
import com.example.Users.entity.Tasks;

@RestController
public class TaskController {

	@Autowired
	private TasksService service;

	@PostMapping("/task")
	public ResponseEntity<?> settask(@RequestBody Tasks tasks) {
		try {

			Tasks tasks2 = this.service.setTask(tasks);

			return new ResponseEntity<>(new Success("Sucess", "Success", tasks2), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("Error in Storing task", "Error"), HttpStatus.BAD_REQUEST);
		}
	}
}
