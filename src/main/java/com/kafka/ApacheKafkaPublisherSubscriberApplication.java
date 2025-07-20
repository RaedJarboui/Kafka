package com.kafka;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.model.Product;

@SpringBootApplication
@RestController
public class ApacheKafkaPublisherSubscriberApplication {
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	private String topicString = "raed-topic-string";
	private String topicJson = "raed-topic-json";
	private List<String> messages = new ArrayList<String>();
	
	
	@GetMapping("/publish/{name}")
	public String publishStringMessageToTopic(@PathVariable String name) {
		template.send(topicString,"Hi my name is " + name);
		return "message type string published to topic";
	}

	
	@GetMapping("/publish/json")
	public String publishJsonMessageToTopic() {
		template.send(topicJson,new Product(1,"product",1f));
		return "message type json published to topic";
	}
	
	@KafkaListener(groupId = "consumerString",topics ="raed-topic-string",containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMessagesFromTopic(String data){
		System.out.println("data message :  " +data);
		messages.add(data);
		return messages;
		
		
	}
	
	@KafkaListener(groupId = "consumerJson",topics ="raed-topic-json",containerFactory = "productKafkaListenerContainerFactory")
	public Product getMessagesFromTopic(Product product){
		Product p = product;
		System.out.println("Product : "+p.getName());
		return product;
		
		
	}
	
	

	public static void main(String[] args) {
		SpringApplication.run(ApacheKafkaPublisherSubscriberApplication.class, args);
	}

}
