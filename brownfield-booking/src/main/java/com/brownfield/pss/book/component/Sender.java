package com.brownfield.pss.book.component;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component 
public class Sender {
	
	private final KafkaTemplate<String, String> template;
	private final ObjectMapper objectMapper;
	
	public Sender(KafkaTemplate<String, String> template, ObjectMapper objectMapper) {
		this.template = template;
		this.objectMapper = objectMapper;
	}
	
	String queue() {
		return "SearchQ";
	}
	
	String queue1() {
		return "CheckINQ";
	}
	
	
	public void send(Object message){
		try {
			template.send("SearchQ", objectMapper.writeValueAsString(message));
		} catch (JsonProcessingException e) {
			System.err.println("Error has occured while converting event to json!");	
		}
	}
}