package com.example.Users.Kafka;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;



@Configuration
@EnableKafka
@ComponentScan
public class KafkaConsumerConfig {

	
	@Bean
	public ConsumerFactory<String, String> consumerFactory()
	{
		Map<String , Object> map=new HashMap<>();
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		map.put(ConsumerConfig.GROUP_ID_CONFIG, "kafkaServer-1");
		
		return new DefaultKafkaConsumerFactory<>(map);
	}
	
	
	 @Bean
	    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory());
	        return factory;
	    }
	
//	@Bean
//	public ConsumerFactory<String, KafkaUser> consumerJsonFactory()
//	{
//		Map<String , Object> map=new HashMap<>();
//		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
//		map.put(ConsumerConfig.GROUP_ID_CONFIG, "kafkaServer-2");
//		KafkaConsumer<String, Object>  kafkaConsumer= new KafkaConsumer<>(map);
//        kafkaConsumer.subscribe(Collections.singletonList("my-topic"));
//		JsonDeserializer<KafkaUser> deserializer=new  JsonDeserializer<>(KafkaUser.class);
//		deserializer.setRemoveTypeHeaders(false);
//		deserializer.addTrustedPackages("*");
//		deserializer.setUseTypeMapperForKey(true);
//		
//		return new DefaultKafkaConsumerFactory<>(map,new org.apache.kafka.common.serialization.StringDeserializer(),deserializer);
//	}
//	
//	
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, KafkaUser> jsonFactory(){
//		
//		ConcurrentKafkaListenerContainerFactory<String, KafkaUser> factory=new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(consumerJsonFactory());
//		return factory;
//	
//		
//	}
//	

	
	
	

}
