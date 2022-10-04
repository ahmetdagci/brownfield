package com.brownfield.pss.search.component;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Receiver {
	
	private final SearchComponent searchComponent;
	private final ObjectMapper objectMapper;

	public Receiver(SearchComponent searchComponent, ObjectMapper objectMapper) {
		this.searchComponent = searchComponent;
		this.objectMapper = objectMapper;
	}

	String queue() {
		return "SearchQ";
	}
	
	@KafkaListener(topics = "SearchQ",groupId = "checking")
    public void processMessage(String fare) {
		try {
			Map<String, Object> fareMap = objectMapper.readValue(fare,Map.class);
			System.out.println("fareMap"+fareMap);
		    searchComponent.updateInventory((String)fareMap.get("FLIGHT_NUMBER"),(String)fareMap.get("FLIGHT_DATE"),(int)fareMap.get("NEW_INVENTORY"));
	        //call repository and update the fare for the given flight
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
}