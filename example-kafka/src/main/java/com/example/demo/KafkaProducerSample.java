package com.example.demo;

import java.time.Instant;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerSample  implements  ApplicationListener<ContextRefreshedEvent> {

    @SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(StartupApplicationListenerExample.class.getName());

    @Override 
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	Properties properties = new Properties();
    	properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    	properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    	properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    	try (Producer<String, String> producer = new KafkaProducer<>(properties)) {
			String recordValue = "Current time is " + Instant.now().toString();

			ProducerRecord<String, String> record = new ProducerRecord<>("java_topic_3", null, recordValue);

			producer.send(record);
			producer.flush();
		}
    	
    }

}
