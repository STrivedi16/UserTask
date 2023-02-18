package com.example.Users.Redis;

//@RestController
//public class RedisController {
//
//	@Autowired
//	private UsersDao dao;
//
//	@GetMapping("/usertask/{id}")
//	@PreAuthorize("hasAuthority('ShowTask')")
//	// @Cacheable(key = "#id", value = "Users")
//
//	public ResponseEntity<?> getUserTask(@PathVariable("id") int id) {
//		try {
//
//			List<UsersTask> list = this.dao.getUserTask(id);
//
//			return new ResponseEntity<>(
//					new Success(SuccessMessageConstant.USER_TASK_SHOW, SuccessMessageKey.USER_TASK_M031302, list),
//					HttpStatus.OK);
//
//		} catch (ResourceNotFoundException e) {
//
//			return new ResponseEntity<>(
//					new ErrorMessage(ErrorMessageConstant.USER_TASK_NOT_FOUND, ErrorMessageKey.USER_TASK_E031302),
//					HttpStatus.NOT_FOUND);
//
//		}
//	}
//
//}
