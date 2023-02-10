package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.Success;
import com.example.Users.Service.UserTaskService;
import com.example.Users.entity.UsersTaskEntity;
import com.example.Users.entity.UsertaskDTO;

@RestController
public class UserTaskController {

	@Autowired
	private UserTaskService taskService;

	@PostMapping("/usertask")
	@PreAuthorize("hasAuthority('AssignTask')")
	public ResponseEntity<?> assignTask(@RequestBody UsertaskDTO dto) {

		try {

			UsersTaskEntity taskEntity = this.taskService.addtaskuser(dto);

			return new ResponseEntity<>(new Success("Success", "Success", taskEntity), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(new ErrorMessage("Something wrong , You can not assign task to user", "Error"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PatchMapping("/updatestatus/{id}")
	@PreAuthorize("hasAuthority('UpdateStatus')")
	public ResponseEntity<?> updateUserTaskStatus(@PathVariable("id") int id, @RequestBody UsertaskDTO dto) {
		System.err.println(dto.getStatus());
		try {

			UsersTaskEntity entity = this.taskService.updatestatus(id, dto);

			return new ResponseEntity<>(new Success("Success", "Success", entity), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(new ErrorMessage("You Cant chang status of Working status", "Error"),
					HttpStatus.BAD_REQUEST);
		}
	}

//	@PatchMapping("/rating/{id}")
//	@PreAuthorize("hasAuthority('GiveRating')")
//	public ResponseEntity<?> giverating(@PathVariable("id") int id, @RequestBody UserTaskStatusDTO dto) {
//		System.err.println(dto.getRateby() + " " + dto.getRating());
//		try {
//
//			UsersTaskEntity entity = this.taskService.Giverating(id, dto);
//
//			return new ResponseEntity<>(new Success("Success", "Success", entity), HttpStatus.OK);
//
//		} catch (Exception e) {
//			return new ResponseEntity<>(new ErrorMessage("Task is pending", "Task is pending"), HttpStatus.BAD_REQUEST);
//		}
//	}
}
