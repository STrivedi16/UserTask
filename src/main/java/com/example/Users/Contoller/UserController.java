package com.example.Users.Contoller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Config.JwtFilter;
import com.example.Users.DTO.UserDto;
import com.example.Users.Interface.UserInterface;
import com.example.Users.Interface.UserTaskReview;
import com.example.Users.Interface.UsersTask;
import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.ResourceNotFoundException;
import com.example.Users.Responce.Success;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;
import com.example.Users.Service.UsersService;
import com.example.Users.entity.Users;

@RestController
public class UserController {

	@Autowired
	private UsersService service;

	JwtFilter filter = new JwtFilter();

	@PostMapping("/register")
	public ResponseEntity<?> setUsers(@RequestBody UserDto users) {

		if (users.getName().isEmpty() == false && users.getEmail().isEmpty() == false
				&& users.getPassword().isEmpty() == false) {
			try {

				System.err.println(users.getEmail());
				System.err.println(users.getPassword());

				Pattern p = Pattern.compile("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})");

				Matcher matcher = p.matcher(users.getPassword());

				System.out.println(users.getPassword());

				System.err.println(matcher.matches());

				if (matcher.matches()) {

					Users users2 = this.service.register(users);

					return new ResponseEntity<>(
							new Success(SuccessMessageConstant.SUCCESS, SuccessMessageKey.USER_M031101, users2),
							HttpStatus.OK);

				} else {

					return new ResponseEntity<>(
							new ErrorMessage(ErrorMessageConstant.INVALID_PASSWORD, ErrorMessageKey.USER_E031102),
							HttpStatus.NOT_ACCEPTABLE);
				}
			} catch (Exception e) {
				return new ResponseEntity<>(
						new ErrorMessage(ErrorMessageConstant.NOT_STORED, ErrorMessageKey.USER_E031101),
						HttpStatus.NOT_ACCEPTABLE);
			}
		} else {
			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.DETAILS_INCOPLETE, ErrorMessageKey.USER_E031101),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getData(@PathVariable("id") int id) throws Exception {
		try {

			if (id == filter.id) {
				Users users = this.service.getById(id);
				return new ResponseEntity<>(
						new Success(SuccessMessageConstant.USER_DETAIL, SuccessMessageKey.USER_M031102, users),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.ACCESS_DENIED, "Not Access"),
						HttpStatus.BAD_REQUEST);
			}

		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new ErrorMessage(e.getMessage(), "Not Found"), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/useres")
	@PreAuthorize("hasAuthority('getusers')")
	public ResponseEntity<?> getAllData() {
		try {

			List<UserInterface> list = this.service.getAll();

			return new ResponseEntity<>(
					new Success(SuccessMessageConstant.USER_DETAIL, SuccessMessageKey.USER_M031102, list),
					HttpStatus.OK);

		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new ErrorMessage(e.getMessage(), ErrorMessageKey.USER_E031105),
					HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/usertask/{id}")
	@PreAuthorize("hasAuthority('ShowTask')")
	public ResponseEntity<?> getUserTask(@PathVariable("id") int id) {
		try {

			List<UsersTask> list = this.service.getUserTask(id);

			return new ResponseEntity<>(
					new Success(SuccessMessageConstant.USER_TASK_SHOW, SuccessMessageKey.USER_TASK_M031302, list),
					HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.USER_TASK_NOT_FOUND, ErrorMessageKey.USER_TASK_E031302),
					HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/mytask/{id}")
	public ResponseEntity<?> showUserTask(@PathVariable("id") int id) {
		try {

			if (id == filter.id) {

				List<UsersTask> list = this.service.getUserTask(id);

				return new ResponseEntity<>(
						new Success(SuccessMessageConstant.USER_TASK_SHOW, SuccessMessageKey.USER_TASK_M031302, list),
						HttpStatus.OK);

			}

			else {

				return new ResponseEntity<>(
						new ErrorMessage(ErrorMessageConstant.ACCESS_DENIED, ErrorMessageKey.ACCESS_E030001),
						HttpStatus.BAD_REQUEST);
			}
		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.USER_TASK_NOT_FOUND, ErrorMessageKey.USER_TASK_E031302),
					HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/review/{id}")
	public ResponseEntity<?> showTaskReview(@PathVariable("id") int id) {
		try {

			if (id == filter.id) {
				List<UserTaskReview> review = this.service.showTaskReview(id);

				return new ResponseEntity<>(
						new Success(SuccessMessageConstant.RATING, SuccessMessageKey.USER_TASK_REVIEW_M011102, review),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new ErrorMessage(ErrorMessageConstant.ACCESS_DENIED, ErrorMessageKey.ACCESS_E030001),
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.USER_TASK_RATING, ErrorMessageKey.USER_TASK_E031301),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/reviews/{id}")
	@PreAuthorize("hasAuthority('showReview')")
	public ResponseEntity<?> showAdminReview(@PathVariable("id") int id) {
		try {

			List<UserTaskReview> list = this.service.showTaskReviewForAdmin(id);

			return new ResponseEntity<>(
					new Success(SuccessMessageConstant.RATING, SuccessMessageKey.USER_TASK_REVIEW_M011102, list),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.USER_TASK_RATING, ErrorMessageKey.USER_TASK_REVIEW_E031403),
					HttpStatus.NOT_FOUND);
		}
	}
}
