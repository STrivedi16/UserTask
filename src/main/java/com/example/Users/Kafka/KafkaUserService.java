package com.example.Users.Kafka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntts.configurations.KafakConsumer;

@Service
public class KafkaUserService {

	@Autowired
	private KafkaUserRepo kafkaUserRepo;
	
	
	public KafkaUser  kafkaMessage(KafkaUser  kafkaUser)
	{
		return this.kafkaUserRepo.save(kafkaUser);
	}
	
	public List<KafkaUser> getList()
	{
		return this.kafkaUserRepo.findAll();
	}
}
