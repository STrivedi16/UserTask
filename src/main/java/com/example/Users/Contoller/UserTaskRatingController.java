package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.Success;
import com.example.Users.Service.UserTaskRatingService;
import com.example.Users.entity.UserTaskRatingEntity;

@RestController
public class UserTaskRatingController {

	@Autowired
	private UserTaskRatingService ratingService;

	@PostMapping("/rating")
	@PreAuthorize("hasAuthority('GiveRating')")
	public ResponseEntity<?> setRating(@RequestBody UserTaskRatingEntity entity) {
		try {

			System.err.println(entity.getUsertask().getStatus());

			UserTaskRatingEntity entity2 = this.ratingService.setrating(entity);

			return new ResponseEntity<>(new Success("Success", "Success", entity2), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(new ErrorMessage("Error in storing Review", "Review Not Stored"),
					HttpStatus.BAD_REQUEST);
		}
	}

}
