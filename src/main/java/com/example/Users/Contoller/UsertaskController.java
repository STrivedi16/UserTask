package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.Success;
import com.example.Users.Service.UserTaskService;
import com.example.Users.entity.UsersTaskEntity;
import com.example.Users.entity.UsertaskDTO;

@RestController
public class UsertaskController {

	@Autowired
	private UserTaskService taskService;

	@PostMapping("/usertask")
	public ResponseEntity<?> assigntask(@RequestBody UsertaskDTO dto) {
		System.out.println(dto.getTaskid() + dto.getUserid());

		try {

			UsersTaskEntity taskEntity = this.taskService.addtaskuser(dto);

			return new ResponseEntity<>(new Success("Success", "Success", taskEntity), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(new ErrorMessage("Something wrong , You can not assign task to user", "Error"),
					HttpStatus.BAD_REQUEST);
		}
	}
}
