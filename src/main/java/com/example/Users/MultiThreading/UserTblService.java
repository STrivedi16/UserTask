package com.example.Users.MultiThreading;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.io.exceptions.IOException;
@Service
public class UserTblService {

	@Autowired
	private UserTblRepo tblRepo;
	
	Logger logger=LoggerFactory.getLogger(UserTblService.class);
	
	@Async
	public CompletableFuture<List<UserTbl>> saveUser(MultipartFile file) throws Exception
	{
		System.err.println("adasdsdasdad");
		
		long start=System.currentTimeMillis();
		List<UserTbl> list=parseCSVFile(file);
		System.err.println("123123123");
		logger.info("Saving the list of size {}", list.size()," " +Thread.currentThread().getName());
		list=this.tblRepo.saveAll(list);
		long end=System.currentTimeMillis();
		System.err.println("123123123");
		logger.info("Totel time {}",(end-start));
		return CompletableFuture.completedFuture(list);
	}
	
	@Async
	public CompletableFuture<List<UserTbl>> getAllUser(){
		List<UserTbl> list=this.tblRepo.findAll();
		logger.info("Get list of users", Thread.currentThread().getName());	
		return CompletableFuture.completedFuture(list);
	}
	
	private List<UserTbl> parseCSVFile(final MultipartFile file) throws Exception {
        final List<UserTbl> users = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final UserTbl user = new UserTbl();
                    user.setName(data[0]);
                    user.setEmail(data[1]);
                    user.setGender(data[2]);
                    users.add(user);
                }
                return users;
            }
        } catch (final IOException e) {
            logger.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }
	
}
