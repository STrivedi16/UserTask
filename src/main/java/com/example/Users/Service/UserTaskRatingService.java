package com.example.Users.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Repository.UserTaskRatingRepository;
import com.example.Users.entity.Status;
import com.example.Users.entity.UserTaskRatingEntity;
import com.example.Users.entity.UsersTaskEntity;

@Service
public class UserTaskRatingService {

	@Autowired
	private UserTaskRatingRepository ratingRepository;

	private UsersTaskEntity taskEntity;

	public UserTaskRatingEntity setrating(UserTaskRatingEntity entity) throws Exception {

		System.err.println(entity.getUsertask().getStatus());

		if (taskEntity.getStatus().equals(Status.DONE)) {

			return this.ratingRepository.save(entity);
		}

		throw new Exception("Not Stord  in ");
	}

}
