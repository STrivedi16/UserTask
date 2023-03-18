package com.example.Users.Kafka;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.http.protocol.HTTP;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.checkerframework.checker.units.qual.m;
import org.junit.platform.engine.reporting.ReportEntry;
import org.slf4j.Logger;
import java.time.Duration;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.Success;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntts.configurations.KafakConsumer;

import antlr.collections.List;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.ForField.Read;

@RestController
public class KafkaController {

	Logger logger=LoggerFactory.getLogger(KafkaController.class);
	
	 @Autowired
	    private ObjectMapper objectMapper;

	    @Autowired
	    private KafkaUserService service;
	
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
	public ResponseEntity<?> publishData(@RequestBody  KafkaUser kafkaUser) 
	{
		try {
			
			
			
			
			kafkaTemplate.send(topic,new KafkaUser(3, "Shivangi", "Ahemdabad"));
			return new ResponseEntity<>(new Success("\"Json data Has been send to kafka server\"", topic, "Data Stored"),HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("Not send in  kafka", "Not send to kafka"),HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	java.util.List<String> list=new ArrayList<>();
	
	@KafkaListener(groupId = "kafkaServer-1",topics = "my-topic",containerFactory = "kafkaListenerContainerFactory")
	public void getMessage(String data) throws JsonMappingException, JsonProcessingException
	{
	    System.out.println("Received Kafka message: " + data);
	    logger.info("Received Kafka message: " + data);
        // TODO: do something with the KafkaUser object, such as saving it to a database
        list.add(data);
        System.err.println(data);
		
	}
	
	@GetMapping("/consumeMsg")
	public java.util.List<String>  conMsg()
	{
		return list;
	}
 	
	//KafkaUser user=null;

	ArrayList al= new ArrayList<>();
	
	private KafkaUser message;
	
   
    
//    @KafkaListener(groupId = "kafkaServer-2", topics = "my-topic", containerFactory = "jsonFactory")
//    public void consumeJson(KafkaUser kafkaUser) {
//    	
//    	
//    	
//    	
//    	
//        System.err.println("Received Kafka message: " + kafkaUser);
//        // TODO: do something with the KafkaUser object, such as saving it to a database
//        
//        this.service.kafkaMessage(kafkaUser);
//        
//        
//    }
//   
    
    
    
	@GetMapping("/messages")
    public ResponseEntity<?> getKafkaMessages() {
       
		
		try {
			
			java.util.List<KafkaUser> kafkaUsers=this.service.getList();
			return new ResponseEntity<>(new Success("Success", "Success", kafkaUsers),HttpStatus.OK);
			
			
		}catch (Exception e) {
		
			return new ResponseEntity<>(new ErrorMessage("Not get", "not get"),HttpStatus.BAD_REQUEST);
		}
		
    }

}
