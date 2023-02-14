package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Repository.UserTaskRatingRepository;
import com.example.Users.Repository.UserTaskRepo;
import com.example.Users.entity.Status;
import com.example.Users.entity.UserRatingDto;
import com.example.Users.entity.UserTaskRatingEntity;
import com.example.Users.entity.UsersTaskEntity;

@Service
public class UserTaskRatingService {

	@Autowired
	private UserTaskRatingRepository ratingRepository;

	@Autowired
	private UserTaskRepo repo;

	public UserTaskRatingEntity setRating(UserRatingDto dto) throws Exception {

		UserTaskRatingEntity entities = new UserTaskRatingEntity();

		UsersTaskEntity entity2 = this.repo.findById(dto.getTask())
				.orElseThrow(() -> new Exception("Error in Storing value"));

		if (entity2.getStatus().equals(Status.DONE)) {
			entities.setId(dto.getId());
			entities.setMessage(dto.getMesssage());
			entities.setRating(dto.getRating());
			entities.setUsertask(entity2);
			entities.setRatedby(dto.getRatedby());
			return this.ratingRepository.save(entities);
		}

		throw new Exception("Error in String rating");

	}

}
