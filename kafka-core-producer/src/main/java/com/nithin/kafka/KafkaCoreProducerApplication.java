package com.nithin.kafka;

import com.nithin.kafka.entity.*;
import com.nithin.kafka.producer.*;
import com.nithin.kafka.service.ImageService;
import org.apache.tomcat.util.net.SSLImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.awt.Image;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableScheduling
public class KafkaCoreProducerApplication implements CommandLineRunner {

	@Autowired
	private Employee2JsonProducer employeeJsonProducer;


//	@Autowired
//	private KafkaKeyProducer kafkaKeyProducer;

//	@Autowired
//	private PurchaseRequestProducer purchaseRequestProducer;
//	@Autowired
//	private PaymentRequestProducer paymentRequestProducer;

//	@Autowired
//	private FoodOrderProducer foodOrderProducer;
//
//	@Autowired
//	private SimpleNumberProducer simpleNumberProducer;

	@Autowired
	private Image2Producer imageProducer;

	@Autowired
	private ImageService imageService;



	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var image1=imageService.generateImage("jpg");
		var image2=imageService.generateImage("png");
		var image3=imageService.generateImage("svg");
		var image4=imageService.generateImage("jpg");
		var image5=imageService.generateImage("gif");
		var image6=imageService.generateImage("jpg");

		imageProducer.send(image1,0);
		imageProducer.send(image2,0);
		imageProducer.send(image3,0);

		imageProducer.send(image4,1);
		imageProducer.send(image5,1);
		imageProducer.send(image6,1);

	}
}
