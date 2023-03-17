package com.example.Users.Kafka;

import java.util.ArrayList;
import java.util.Collection;

import org.checkerframework.checker.units.qual.m;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntts.configurations.KafakConsumer;

import antlr.collections.List;
import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.ForField.Read;

@RestController
public class KafkaController {

	Logger logger=LoggerFactory.getLogger(KafkaController.class);
	
	@Autowired
	private KafkaTemplate<String , Object> kafkaTemplate;
	
	
	public String topic ="my-topic";
	
	@GetMapping("/kafka/{name}")
	public String publishMessage(@PathVariable("name") String name)
	{
		kafkaTemplate.send(topic,"Hi "+name+" Welcome to kafka application");
		return "Data send to kafka server";
	}
	
	@GetMapping("/kafka")
	public String publishData()
	{
		kafkaTemplate.send(topic,new KafkaUser(1, "Shubham", "Rajkot"));
		return "Json data Has been send to kafka server";
	}
	
	java.util.List<String> list=new ArrayList<>();
	
	@KafkaListener(groupId = "kafkaServer-1",topics = "my-topic",containerFactory = "factory")
	@GetMapping("/StringValue")
	public java.util.List<String> getMessage(String data) throws JsonMappingException, JsonProcessingException
	{
		System.out.println(data);
			ObjectMapper mapper=new ObjectMapper();
			mapper.readValue(data, java.util.List.class);
			list.addAll((Collection<? extends String>) mapper);
			System.out.println(mapper);
			
			return list;
	}
	
	@GetMapping("/consumeMsg")
	public java.util.List<String>  conMsg()
	{
		return list;
	}
 	
	KafkaUser user=null;
	@KafkaListener(groupId = "kafkaServer-2",topics = "my-topic" )
	@GetMapping("/userdata")
	public String  getJsonMessage(String  kafkaUser) throws JsonMappingException, JsonProcessingException
	{
	
		System.err.println(kafkaUser);
		
		ObjectMapper mapper=new ObjectMapper();
		mapper.readValue(kafkaUser, KafkaUser.class);
		logger.info("User data get"+kafkaUser);
		
		
		return mapper.toString();
		
		
	} 
//	
//	@GetMapping("/ConsumeJson")
//	public KafkaUser kafkaUser()
//	{
//		System.out.println(user);
//		return user;
//	}
}
