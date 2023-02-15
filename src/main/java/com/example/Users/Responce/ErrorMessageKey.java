package com.example.Users.Responce;

public class ErrorMessageKey {

	public static final String HEADER = "UT-E03";

	public static final String ACCESS_DENIED = "00";
	public static final String USER = "11";
	public static final String TASK = "12";
	public static final String USER_TASK = "13";
	public static final String ROLE = "15";
	public static final String USER_ROLE = "16";
	public static final String PERMISSION = "17";
	public static final String ROLE_PERMISSION = "18";
	public static final String USER_TASK_REVIEW = "14";
	public static final String STAUS = "19";
	public static final String FILE = "20";

	// http request
	public static final String REQUEST_E0301 = HEADER + "01";

	// Access Denied
	public static final String ACCESS_E030001 = HEADER + ACCESS_DENIED + "01";// ACCESS DENIED

	// FOR USER
	public static final String USER_E031100 = "UT-E03" + USER + "00";// NOT LOGIN
	public static final String USER_E031101 = "UT-E03" + USER + "01"; // NOT STORED
	public static final String USER_E031102 = HEADER + USER + "02";// INVALID PASSWORD
	public static final String USER_E031103 = HEADER + USER + "03";// // INVALID USERNME OR PASSWORD
	public static final String USER_E031104 = HEADER + USER + "04";// INVLAID TOKEN
	public static final String USER_E031105 = HEADER + USER + "05";// user not get

	// TASK
	public static final String TASK_E031201 = HEADER + TASK + "01";// TASK NOT STORED
	public static final String TASK_E031202 = HEADER + TASK + "02";// NOT FOUND

	// USER TASK
	public static final String USER_TASK_E031301 = HEADER + USER_TASK + "01";// USER TASK NOT STORED
	public static final String USER_TASK_E031302 = HEADER + USER_TASK + "02";// USER TASK NOT GET

	// STATUS
	public static final String STATUS_E031903 = HEADER + STAUS + "01";// SATATUS NOT UPDATE

	// RATING
	public static final String USER_TASK_REVIEW_E031403 = HEADER + USER_TASK_REVIEW + "03";// Rating

	public static final String USER_TASK_REVIEW_E031401 = HEADER + USER_TASK_REVIEW + "01";

	public static final String PERMISSION_E031701 = HEADER + PERMISSION + "01";// PERMISSION NOT STORED

	public static final String ROLE_E031501 = HEADER + ROLE + "01";// role not stored

	public static final String ROlE_PERMISSION_E031801 = HEADER + ROLE_PERMISSION + "01";// ROLE PERMISSION NOT STORED

	public static final String USER_ROLE_E031601 = HEADER + USER_ROLE + "01";// user role

	// file
	public static final String FILE_E032001 = HEADER + FILE + "01";// file not upload

	public static final String FILE_E032002 = HEADER + FILE + "02";// file data not fetched
}
