package com.example.Users.Service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ForScheduling  extends RuntimeException{

	Logger logger=LoggerFactory.getLogger(ForScheduling.class);
	
	@Scheduled(cron = "0 0 * * * *")
	public void  test()
	{
		
		logger.info("Something is worng Please check you application ");
		
	}
	
}
