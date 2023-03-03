package com.example.Users.MultiThreading;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserTblController {

	@Autowired
	private UserTblService service;
	
	@PostMapping(value = "/userTbl",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = "application/json")
	public ResponseEntity<?> saveUser(@RequestParam("files") MultipartFile files) throws Exception
	{
		System.err.println(files);
//		 for(MultipartFile file: files)
//		 {
			 System.err.println("files data get");
			 this.service.saveUser(files);
			 
			 
			 
		 //}
		 return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(value = "/get",produces = "application/json")
	public CompletableFuture<ResponseEntity> findAllUser(){
		return service.getAllUser().thenApply(ResponseEntity::ok);
	}
	
}
