package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.Success;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;
import com.example.Users.Service.TasksService;
import com.example.Users.entity.Tasks;

@RestController
public class TaskController {

	@Autowired
	private TasksService service;

	@PostMapping("/task")
	@PreAuthorize("hasAuthority('setTask')")
	public ResponseEntity<?> setTask(@RequestBody Tasks tasks) {
		try {

			Tasks tasks2 = this.service.setTask(tasks);

			return new ResponseEntity<>(
					new Success(SuccessMessageConstant.TASK_ADD, SuccessMessageKey.TASK_M031201, tasks2),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.TASK_NOT_STORED, ErrorMessageKey.TASK_E031201),
					HttpStatus.BAD_REQUEST);
		}
	}
}
