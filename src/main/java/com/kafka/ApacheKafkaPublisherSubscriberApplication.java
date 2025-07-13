package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	
	private String topic = "raed-topic";
	
	
	@GetMapping("/publish/{name}")
	public String publishStringMessageToTopic(@PathVariable String name) {
		template.send(topic,"Hi my name is " + name);
		return "message type string published to topic";
	}

	
	@GetMapping("/publish/json")
	public String publishJsonMessageToTopic() {
		template.send(topic,new Product(1,"product",1f));
		return "message type json published to topic";
	}

	public static void main(String[] args) {
		SpringApplication.run(ApacheKafkaPublisherSubscriberApplication.class, args);
	}

}
