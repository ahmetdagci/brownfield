package com.example.demo;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListenerExample implements  ApplicationListener<ContextRefreshedEvent> {

    @SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(StartupApplicationListenerExample.class.getName());

    public static int counter;

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {
    	
    	Properties properties = new Properties();
    	properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    	properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    	properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    	properties.put(ConsumerConfig.GROUP_ID_CONFIG, "my-first-consumer-group");
    	properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

    	properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    	
    	Consumer<String, String> consumer = new KafkaConsumer<>(properties);
    	
    	consumer.subscribe(Collections.singleton("java_topic_3"));

    	while (true) {
    	    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

    	    for (ConsumerRecord<String, String> record : records) {
    	        System.out.println("Message received: " + record.value());
    	    }
    	    consumer.commitAsync();
    	}
    
    }
}