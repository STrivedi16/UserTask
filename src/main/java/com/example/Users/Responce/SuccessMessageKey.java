package com.example.Users.Responce;

public class SuccessMessageKey {

	public static final String HEADER = "UT-M03";

	public static final String USER = "11";
	public static final String TASK = "12";
	public static final String USER_TASK = "13";
	public static final String ROLE = "15";
	public static final String USER_ROLE = "16";
	public static final String PERMISSION = "17";
	public static final String ROLE_PERMISSION = "18";
	public static final String USER_TASK_REVIEW = "14";
	public static final String STAUS = "19";

	public static final String LOGIN_M031100 = HEADER + USER + "00";// login
	public static final String USER_M031101 = HEADER + USER + "01";// STORED
	public static final String USER_M031102 = HEADER + USER + "02";// USER DETAILS FETCHED

	public static final String USER_TASK_M031301 = HEADER + USER_TASK + "01";// USERTASK ASSIGN
	public static final String USER_TASK_M031302 = HEADER + USER_TASK + "02";// USER TASK FETCHED

	public static final String USER_TASK_REVIEW_M011101 = HEADER + USER_TASK_REVIEW + "01";// user task review stored

	public static final String USER_TASK_REVIEW_M011102 = HEADER + USER_TASK_REVIEW + "02";// user task review get

	public static final String PERMISSION_M0313701 = HEADER + PERMISSION + "01";// PERMISSION STORED

	public static final String ROLE_M031501 = HEADER + ROLE + "01";// role stored

	public static final String USER_ROLE_M031601 = HEADER + USER_ROLE + "01";// user role

	public static final String ROLE_PERMISSION_M031801 = HEADER + ROLE_PERMISSION + "01";// ADD ROLE PERMISSION
}
