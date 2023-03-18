package com.example.Users.Kafka;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaUserRepo extends JpaRepository<KafkaUser, Integer>{

}
