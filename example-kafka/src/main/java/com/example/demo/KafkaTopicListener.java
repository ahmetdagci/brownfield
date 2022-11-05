package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopicListener {

	@KafkaListener(topics = "java_topic_3",groupId = "my-first-consumer-group")
    public void listen(String data) {
        System.out.println("@KafkaListener -> "+ data);
    }
}
