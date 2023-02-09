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
import com.example.Users.entity.UserRatingDTO;
import com.example.Users.entity.UserTaskRatingEntity;

@RestController
public class UserTaskRatingController {

	@Autowired
	private UserTaskRatingService ratingService;

	@PostMapping("/rating")
	@PreAuthorize("hasAuthority('GiveRating')")
	public ResponseEntity<?> setRating(@RequestBody UserRatingDTO dto) {
		try {

			System.err.println(dto.getTask());
			System.err.println(dto.getMesssage() + " " + dto.getRating() + " " + dto.getRatedby() + " " + dto.getId()
					+ " " + dto.getTask());

			UserTaskRatingEntity entity2 = this.ratingService.setrating(dto);

			return new ResponseEntity<>(new Success("Success", "Success", entity2), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(new ErrorMessage("You can't Give Reivew it may not  Completed or not get ",
					"Task not Completed or not Assign"), HttpStatus.BAD_REQUEST);
		}
	}

}
