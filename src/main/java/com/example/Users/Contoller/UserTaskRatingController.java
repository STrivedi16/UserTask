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

			return new ResponseEntity<>(
					new Success(SuccessMessageConstant.RATING, SuccessMessageKey.USER_TASK_REVIEW_M011101, entity2),
					HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.USER_TASK_RATING, ErrorMessageKey.USER_TASK_REVIEW_E031401),
					HttpStatus.BAD_REQUEST);
		}
	}

}
