package com.example.Users.MultiThreading;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

	@Bean(name = "taskExecutor")	
	public Executor executor(){
		ThreadPoolTaskExecutor poolTaskExecutor=new  ThreadPoolTaskExecutor();
		poolTaskExecutor.setCorePoolSize(2);
		poolTaskExecutor.setMaxPoolSize(2);
		poolTaskExecutor.setQueueCapacity(100);
		poolTaskExecutor.setThreadNamePrefix("userThread-");
		poolTaskExecutor.initialize();
		
		return poolTaskExecutor;
	}
	
	
}
