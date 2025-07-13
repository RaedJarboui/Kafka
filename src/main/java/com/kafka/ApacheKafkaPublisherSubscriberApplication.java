package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApacheKafkaPublisherSubscriberApplication {
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	private String topic = "raed-topic";
	
	
	@GetMapping("/publish/{name}")
	public String publishMessageToTopic(@PathVariable String name) {
		template.send(topic,"Hi my name is " + name);
		return "hello";
	}

	public static void main(String[] args) {
		SpringApplication.run(ApacheKafkaPublisherSubscriberApplication.class, args);
	}

}
