package com.example.Users.Contoller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Config.JwtFilter;
import com.example.Users.Config.PasswordVerification;
import com.example.Users.DTO.LoginOtpDto;
import com.example.Users.DTO.UserDto;
import com.example.Users.Interface.UserInterface;
import com.example.Users.Interface.UserTaskReview;
import com.example.Users.Interface.UsersTask;
import com.example.Users.OTP.OtpService;
import com.example.Users.OTP.SendOtp;
import com.example.Users.Repository.TempUserTableRepository;
import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.ResourceNotFoundException;
import com.example.Users.Responce.Success;
import com.example.Users.Responce.SuccessFileMessage;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;
import com.example.Users.Responce.SuccessMessageToken;
import com.example.Users.Service.EmailService;
import com.example.Users.Service.TempUserDatabase;
import com.example.Users.Service.UsersService;
import com.example.Users.entity.TempUserDto;
import com.example.Users.entity.Users;

@RestController
public class UserController {

	@Autowired
	private UsersService service;

	JwtFilter filter = new JwtFilter();
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private TempUserDatabase database;
	
	
	@Autowired
	private EmailService emailService;
	
	
	@Autowired
	private PasswordVerification passwordVerification;
//
//	@Autowired
//	private UsersDao dao;

	OtpVerification otpVerification=new OtpVerification();
	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> setUsers(@RequestBody UserDto users) throws Exception {

		if (users.getName().isEmpty() == false && users.getEmail().isEmpty() == false
				&& users.getPassword().isEmpty() == false) {
			try {

					
				
				
				System.err.println(users.getEmail());

				
				
				Boolean passwordVerify=passwordVerification.passwordverify(users.getPassword());
				
				SendOtp otp=otpService.setOtpForVerify(users.getEmail());
				
				String message="OTP = "+otpService.newotp;
				
				String subject="OTP for verification";
				
				String to = users.getEmail();
				
				
			
			
//			if(otpVerification.verifyOtp())
				

				if (passwordVerify==true) {
					
					emailService.sendEmail(subject, message, to);
					
					database.dto(users);

//					UserDto users2 = this.service.register(users);

//					return new ResponseEntity<>(
//							new Success(SuccessMessageConstant.SUCCESS, SuccessMessageKey.USER_M031101, users2),
//							HttpStatus.OK);
					
					
					return new ResponseEntity<>(new SuccessFileMessage("please enter otp that you have got in email for verification", "OTP sent to your email id "),HttpStatus.OK);

				} else {

					return new ResponseEntity<>(
							new ErrorMessage(ErrorMessageConstant.INVALID_PASSWORD, ErrorMessageKey.USER_E031102),
							HttpStatus.NOT_ACCEPTABLE);
				}
			} catch (ResourceNotFoundException e) {
				return new ResponseEntity<>(
						new ErrorMessage(ErrorMessageConstant.NOT_STORED, ErrorMessageKey.USER_E031101),
						HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.DETAILS_INCOPLETE, ErrorMessageKey.USER_E031101),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/user/{id}")
	//@Cacheable(key = "#id", value = "Users")
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
	public ResponseEntity<?> getAllData(
			@RequestParam(name = "pagenumber" , defaultValue = "0", required = false)Integer pagenumber,
			@RequestParam(name="pagesize",defaultValue = "5",required =false)Integer pagesize)
		
	{
		try {

			List<UserInterface> list = this.service.getAll(pagesize, pagenumber);

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
	// @Cacheable(key = "#id", value = "Users")

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
	
	
	@PostMapping("/registerOtp")
	public ResponseEntity<?> loginWithOtp(@RequestBody LoginOtpDto dto)
	{
		TempUserDto user = null;
		try {
			
			SendOtp otp=this.otpService.findEmail(dto.getEmail(), dto.getOtp());
			
			System.err.println("11313123123123");

			
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			
			if(ts.compareTo(otp.getOtpReqestTime())==-1)
			{

				if(otp==null)
				{
					return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.LOGIN_FAIL, ErrorMessageKey.USER_E031100),HttpStatus.BAD_REQUEST);
				}
				
				TempUserDto d=this.database.getData(dto.getEmail());
				
				
				
				 user=this.service.register(d);
				
				
				
				return new ResponseEntity<>(new Success(SuccessMessageConstant.SUCCESS, SuccessMessageKey.USER_M031101, user),HttpStatus.OK);
			}
			
			else {
				return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.NOT_STORED, ErrorMessageKey.USER_E031101),HttpStatus.BAD_REQUEST);

			}
			
			
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.NOT_STORED, ErrorMessageKey.USER_E031100),HttpStatus.BAD_REQUEST);
		}
		
		finally {
			this.database.clearDto(user.getEmail());
			
			System.err.println("user data adaldnasda");
			
		otpService.clearOtp(dto.getEmail(), dto.getOtp());
		}

	}
	
	
}
