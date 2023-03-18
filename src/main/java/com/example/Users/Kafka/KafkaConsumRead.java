package com.example.Users.Kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumRead {

	Logger logger=LoggerFactory.getLogger(KafkaConsumRead.class);
	
//	@KafkaListener(groupId = "kafkaServer-2",topics = "my-topic" )
//	public String getJsonMessage(String user) throws JsonMappingException, JsonProcessingException
//	{
//		System.err.println(user);
//		
//		ObjectMapper mapper=new ObjectMapper();
//		mapper.readValue(user, KafkaUser.class);
//		logger.info("User data get"+user);
//		
//		return mapper.toString();
//		
//	}
	
	 @KafkaListener(topics = "my-topic", groupId = "kafkaServer-2")
	    public void consume(String message) {
	        System.out.println("Received message: " + message);
	    }

	
	 
	 
	 
}
